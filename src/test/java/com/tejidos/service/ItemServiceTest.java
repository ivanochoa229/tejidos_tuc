package com.tejidos.service;

import com.tejidos.persistence.entity.Item;
import com.tejidos.persistence.repository.ItemRepository;
import com.tejidos.presentation.dto.request.ItemRequest;
import com.tejidos.presentation.dto.response.ItemResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/ItemUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void saveItemTest_successful(){
        String descriptionItem = "semigrueso blanco", expectedResponse = "Item created successfully";
        Double priceItem = 10.0, quantityItem = 45.0;
        Long idCategory = 1L, idUnit = 1L;
        ItemRequest request = new ItemRequest(descriptionItem, priceItem, idUnit, idCategory, quantityItem);
        String response = itemService.saveItem(request);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void saveItemTest_whenDescriptionItemAlreadyExists(){
        String descriptionItem = "semigrueso rojo", expectedResponse = "Item created successfully";
        Double priceItem = 10.0, quantityItem = 45.0;
        Long idCategory = 1L, idUnit = 1L;
        ItemRequest request = new ItemRequest(descriptionItem, priceItem, idUnit, idCategory, quantityItem);
        assertThrows(DataIntegrityViolationException.class, () -> itemService.saveItem(request));
    }

    @Test
    public void findByIdTest_successful(){
        Long idItem = 1001L;
        String expectedDescriptionItem = "semigrueso verde", expectedCategory = "HILO", expectedUnit = "KILOGRAM";
        Double expectedPriceItem = 45.0, expectedQuantityItem = 100.0;
        ItemResponse response = itemService.findById(idItem);
        assertEquals(expectedDescriptionItem, response.descriptionItem());
        assertEquals(expectedPriceItem, response.priceItem());
        assertEquals(expectedQuantityItem, response.quantity());
        assertEquals(expectedCategory, response.category());
        assertEquals(expectedUnit, response.unit());
    }

    @Test
    public void findByIdTest_whenItemDoesNotExists(){
        Long idItem = 101L;
        assertThrows(NotFoundException.class, () -> itemService.findById(idItem));
    }

    @Test
    public void findByIdTest_whenItemIsAlreadyDeleted(){
        Long idItem = 1002L;
        assertThrows(NotFoundException.class, () -> itemService.findById(idItem));
    }

    @Test
    public void findEntityByIdTest_successful(){
        Long idItem = 1003L;
        String expectedDescriptionItem = "semigrueso azul";
        Double expectedPriceItem = 45.0, expectedQuantityItem = 100.0;
        Long expectedIdCategory = 1L, expectedIdUnit = 1L;
        Item response = itemService.findEntityById(idItem);
        assertEquals(expectedDescriptionItem, response.getDescriptionItem());
        assertEquals(expectedPriceItem, response.getPriceItem());
        assertEquals(expectedQuantityItem, response.getQuantity());
        assertEquals(expectedIdCategory, response.getCategory().getIdCategory());
        assertEquals(expectedIdUnit, response.getUnit().getIdUnit());
    }

    @Test
    public void findEntityByIdTest_whenItemDoesNotExists(){
        Long idItem = 102L;
        assertThrows(NotFoundException.class, () -> itemService.findEntityById(idItem));
    }

    @Test
    public void deleteItemTest_successful(){
        Long idItem = 1004L;
        String expectedResponse = "Item deleted successfully";
        String response = itemService.deleteItem(idItem);
        assertEquals(expectedResponse, response);
        Item item = itemRepository.findById(idItem).get();
        assertTrue(item.getDeleted());
    }

    @Test
    public void deleteItemTest_whenItemDoesNotExists(){
        Long idItem = 104L;
        assertThrows(NotFoundException.class, () -> itemService.deleteItem(idItem));
    }

    @Test
    public void deleteItemTest_whenItemIsAlreadyDeleted(){
        Long idItem = 1005L;
        assertThrows(NotFoundException.class, () -> itemService.deleteItem(idItem));
    }

    @Test
    public void updateItemTest_successful(){
        Long idItem = 1006L, idCategory = 1L, idUnit = 1L;
        String expectedDescriptionItem = "semigrueso marron";
        Double expectedPriceItem = 45.0, expectedQuantityItem = 100.0;
        ItemRequest request = new ItemRequest(expectedDescriptionItem, expectedPriceItem, idUnit, idCategory, expectedQuantityItem);
        ItemResponse response = itemService.updateItem(request, idItem);
        assertEquals(expectedDescriptionItem, response.descriptionItem());
        assertEquals(expectedPriceItem, response.priceItem());
        assertEquals(expectedQuantityItem, response.quantity());
    }

    @Test
    public void updateItemTest_whenItemDoesNotExists(){
        Long idItem = 106L, idCategory = 1L, idUnit = 1L;
        String expectedDescriptionItem = "semigrueso marron", expectedCategory = "HILO", expectedUnit = "KILOGRAM";
        Double expectedPriceItem = 45.0, expectedQuantityItem = 100.0;
        ItemRequest request = new ItemRequest(expectedDescriptionItem, expectedPriceItem, idUnit, idCategory, expectedQuantityItem);
        assertThrows(NotFoundException.class, () -> itemService.updateItem(request, idItem));
    }

    @Test
    public void updateItemTest_whenItemIsAlreadyDeleted(){
        Long idItem = 1007L, idCategory = 1L, idUnit = 1L;
        String expectedDescriptionItem = "semigrueso marron", expectedCategory = "HILO", expectedUnit = "KILOGRAM";
        Double expectedPriceItem = 45.0, expectedQuantityItem = 100.0;
        ItemRequest request = new ItemRequest(expectedDescriptionItem, expectedPriceItem, idUnit, idCategory, expectedQuantityItem);
        assertThrows(NotFoundException.class, () -> itemService.updateItem(request, idItem));
    }

    @Test
    public void updateItemTest_whenDescriptionItemAlreadyExists(){
        Long idItem = 1008L, idCategory = 1L, idUnit = 1L;
        String expectedDescriptionItem = "semigrueso amarillo", expectedCategory = "HILO", expectedUnit = "KILOGRAM";
        Double expectedPriceItem = 45.0, expectedQuantityItem = 100.0;
        ItemRequest request = new ItemRequest(expectedDescriptionItem, expectedPriceItem, idUnit, idCategory, expectedQuantityItem);
        assertThrows(DataIntegrityViolationException.class, () -> itemService.updateItem(request, idItem));
    }

}
