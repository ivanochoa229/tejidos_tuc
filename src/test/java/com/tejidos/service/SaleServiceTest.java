package com.tejidos.service;

import com.tejidos.persistence.repository.SaleRepository;
import com.tejidos.presentation.dto.response.SaleResponse;
import com.tejidos.utils.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/SaleUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class SaleServiceTest {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleRepository saleRepository;

    @Test
    public void finishSaleTest_successful(){
        Long idSale = 1000L;
        String expectedResponse = "Sale finished successfully";
        String response = saleService.finishSale(idSale);
        assertEquals(expectedResponse, response);
        assertEquals(Status.FINISHED, saleRepository.findById(idSale).get().getStatus());
    }

    @Test
    public void finishSaleTest_whenSaleDoesNotExists(){
        Long idSale = 10L;
        assertThrows(NotFoundException.class, () -> saleService.finishSale(idSale));
    }

    @Test
    public void findByIdTest_successful(){
        Long idSale = 1001L;
        String expectedItem = "semigrueso rojo", expectedStatus = Status.PENDING_PAYMENT.name();
        Double expectedTotal = 450.0;
        SaleResponse response = saleService.findById(idSale);
        assertEquals(expectedItem, response.items().getFirst());
        assertEquals(expectedStatus, response.status());
        assertEquals(expectedTotal, response.total());
    }

    @Test
    public void findByIdTest_whenSaleDoesNotExists(){
        Long idSale = 101L;
        assertThrows(NotFoundException.class, () -> saleService.findById(idSale));
    }

    @Test
    public void findAllByClientTest_successful(){
        Long idClient = 1001L;
        String expectedItem = "semigrueso rojo", expectedStatus = Status.PENDING_PAYMENT.name();
        Double expectedTotal = 10.0;
        SaleResponse response = saleService.findAllByClient(idClient).getFirst();
        assertEquals(expectedItem, response.items().getFirst());
        assertEquals(expectedStatus, response.status());
        assertEquals(expectedTotal, response.total());
    }

    @Test
    public void findAllByClientTest_whenSaleDoesNotExists(){
        Long idClient = 101L;
        assertTrue(saleService.findAllByClient(idClient).isEmpty());
    }


}
