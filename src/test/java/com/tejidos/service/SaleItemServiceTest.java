package com.tejidos.service;

import com.tejidos.persistence.entity.SaleItem;
import com.tejidos.persistence.repository.SaleItemRepository;
import com.tejidos.presentation.dto.SaleItemDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/SaleItemUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class SaleItemServiceTest {

    @Autowired
    private SaleItemService saleItemService;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Test
    public void saveAllTest_successful(){
        Long idItem = 1000L, idSale = 1000L;
        Double quantity = 10.0, price = 10.0, subtotal = 100.0;
        List<SaleItemDTO> request = List.of(new SaleItemDTO(idItem, idSale, quantity, price, subtotal));
        saleItemService.saveAll(request);
        SaleItem response = saleItemRepository.findAll().getFirst();
        assertEquals(idItem, response.getItem().getIdItem());
        assertEquals(idSale, response.getSale().getIdSale());
        assertEquals(quantity, response.getQuantity());
        assertEquals(price, response.getPrice());
        assertEquals(subtotal, response.getSubtotal());
    }

}
