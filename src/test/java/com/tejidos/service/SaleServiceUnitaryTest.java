package com.tejidos.service;

import com.tejidos.persistence.entity.*;
import com.tejidos.persistence.repository.SaleRepository;
import com.tejidos.presentation.dto.SaleLineDetail;
import com.tejidos.presentation.dto.request.*;
import com.tejidos.presentation.dto.response.ItemResponse;
import com.tejidos.service.impl.SaleServiceImpl;
import com.tejidos.utils.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaleServiceUnitaryTest {

    @Mock private SaleRepository saleRepositoryMock;
    @Mock private SaleItemService saleItemServiceMock;
    @Mock private ItemService itemServiceMock;
    @Mock private PaymentService paymentServiceMock;

    @InjectMocks private SaleServiceImpl saleService;

    private Sale createTestSale() {
        Category category = new Category(1L, "TELAS");
        Unit unit = new Unit(1L, "KILOGRAMS");
        Item item = new Item(1L, category, "HILO", 10.0, 20.0, unit );
        Client client =  new Client(1L);
        TypePayment typePayment = new TypePayment(1L, "EFECTIVO");
        Payment payment = new Payment(20.0, "No Observations", typePayment);
        payment.setIdPayment(1L);
        Sale sale = new Sale(10L, client, Status.PENDING_PAYMENT, 20.0);
        SaleItem saleItem = new SaleItem(sale, item, 2.0, 10.0, 20.0);
        sale.setSaleItems(List.of(saleItem));
        sale.setPayment(payment);
        return sale;
    }

    @Test
    void cancelSale_ShouldCancelSaleAndUpdateItems() {
        Long saleId = 10L;
        Sale sale = createTestSale();
        when(saleRepositoryMock.findById(saleId)).thenReturn(Optional.of(sale));
        when(itemServiceMock.updateItem(any(ItemRequest.class), eq(1L)))
                .thenReturn(new ItemResponse(1L, "HILO", 10.0, "KILOGRAMS", "TELAS", 20.0));

        assertEquals("Sale cancelled successfully", saleService.cancelSale(saleId));
        assertEquals(Status.CANCELLED, sale.getStatus());
        verify(itemServiceMock).updateItem(argThat(req -> req.quantity() == 22.0), eq(1L));
        verify(paymentServiceMock).deletePayment(1L);
    }

    @Test
    void cancelSale_WhenNotFound_ShouldThrowException() {
        when(saleRepositoryMock.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> saleService.cancelSale(99L));
    }

    @Test
    void cancelSale_WhenAlreadyCancelled_ShouldStillProcess() {
        Long saleId = 1L;
        Sale sale = createTestSale();

        when(saleRepositoryMock.findById(saleId)).thenReturn(Optional.of(sale));
        when(itemServiceMock.updateItem(any(ItemRequest.class), eq(1L)))
                .thenReturn(new ItemResponse(1L, "HILO", 10.0, "KILOGRAMS", "TELAS", 7.0));

        assertEquals("Sale cancelled successfully", saleService.cancelSale(saleId));
        verify(paymentServiceMock).deletePayment(1L);
    }

    @Test
    void saveSale_ShouldCreateSaleSuccessfully() {
        Long itemId = 1L;
        Item item = new Item(itemId, new Category(1L, "TELAS"), "Test Item", 10.0, 10.0, new Unit(1L, "KILOGRAMS"));
        SaleRequest saleRequest = new SaleRequest(1L, new PaymentRequest("Test payment", 1L, 0.0), List.of(new SaleLineDetail(itemId, 2.0)));
        Payment savedPayment = new Payment();
        savedPayment.setIdPayment(1L);

        when(itemServiceMock.findEntityById(itemId)).thenReturn(item);
        when(saleRepositoryMock.save(any(Sale.class))).thenAnswer(inv -> inv.getArgument(0));
        when(paymentServiceMock.savePayment(any(PaymentRequest.class))).thenReturn(savedPayment);
        when(itemServiceMock.updateItem(any(ItemRequest.class), eq(itemId)))
                .thenReturn(new ItemResponse(itemId, "Test Item", 10.0, "KILOGRAMS", "TELAS", 8.0));

        assertEquals("Sale Created Successfully", saleService.saveSale(saleRequest));
        verify(saleItemServiceMock).saveAll(argThat(list -> list.size() == 1 && list.get(0).idItem() == itemId && list.get(0).quantity() == 2.0));
    }

    @Test
    void saveSale_WhenInsufficientStock_ShouldThrowException() {
        Long itemId = 1L;
        Item item = new Item(itemId, new Category(1L, "TELAS"), "Test Item", 15.0, 10.0, new Unit(1L, "KILOGRAMS"));
        SaleRequest saleRequest = new SaleRequest(1L, new PaymentRequest("Test payment", 1L, 0.0), List.of(new SaleLineDetail(itemId, 15.0)));

        when(itemServiceMock.findEntityById(itemId)).thenReturn(item);
        assertThrows(IllegalArgumentException.class, () -> saleService.saveSale(saleRequest));
        verify(saleRepositoryMock, never()).save(any());
    }

    @Test
    void saveSale_WhenItemNotFound_ShouldThrowException() {
        when(itemServiceMock.findEntityById(99L)).thenThrow(new NotFoundException("Item not found"));
        assertThrows(NotFoundException.class, () -> saleService.saveSale(new SaleRequest(1L, new PaymentRequest("Test", 1L, 0.0), List.of(new SaleLineDetail(99L, 1.0)))));
    }

    @Test
    void saveSale_WhenPaymentFails_ShouldRollback() {
        Long itemId = 1L;
        Item item = new Item(itemId, new Category(1L, "TELAS"), "Test Item", 10.0, 10.0, new Unit(1L, "KILOGRAMS"));
        SaleRequest saleRequest = new SaleRequest(1L, new PaymentRequest("Test payment", 1L, 0.0), List.of(new SaleLineDetail(itemId, 2.0)));

        when(itemServiceMock.findEntityById(itemId)).thenReturn(item);
        when(paymentServiceMock.savePayment(any(PaymentRequest.class))).thenThrow(new RuntimeException("Error de conexión con gateway de pago"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> saleService.saveSale(saleRequest));
        assertEquals("Error de conexión con gateway de pago", exception.getMessage());
        verify(paymentServiceMock).savePayment(any(PaymentRequest.class));
        verify(itemServiceMock, never()).updateItem(any(ItemRequest.class), anyLong());
    }
}
