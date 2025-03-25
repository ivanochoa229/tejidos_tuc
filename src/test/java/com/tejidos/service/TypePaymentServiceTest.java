package com.tejidos.service;

import com.tejidos.presentation.dto.response.CategoryResponse;
import com.tejidos.presentation.dto.response.TypePaymentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/TypePaymentUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class TypePaymentServiceTest {

    @Autowired
    private TypePaymentService paymentService;

    @Test
    public void findByIdTest_successful(){
        Long idTypePayment = 1L;
        String expectedTypePayment = "EFECTIVO";
        TypePaymentResponse response = paymentService.findById(idTypePayment);
        assertEquals(expectedTypePayment, response.typePayment());
    }

    @Test
    public void findByIdTest_whenTypePaymentDoesNotExists(){
        Long idTypePayment = 10L;
        assertThrows(NotFoundException.class, ()-> paymentService.findById(idTypePayment));
    }

    @Test
    public void findAll_successful(){
        List<String> expectedNames = Arrays.asList("EFECTIVO", "DEBITO", "CREDITO");
        List<TypePaymentResponse> response = paymentService.findAll();
        assertEquals(expectedNames.size(), response.size());
        for (int i = 0; i < response.size(); i++) {
            assertEquals(expectedNames.get(i), response.get(i).typePayment());
        }
    }

}
