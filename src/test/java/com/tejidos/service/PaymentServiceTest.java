package com.tejidos.service;

import com.tejidos.persistence.entity.Payment;
import com.tejidos.persistence.repository.PaymentRepository;
import com.tejidos.presentation.dto.request.PaymentRequest;
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
@Sql(scripts = {"/db_templates_test/PaymentUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void savePaymentTest_successful(){
        Long idTypePayment = 1000L;
        Double totalPayment = 500.0;
        String descriptionPayment = "No observations";
        PaymentRequest request = new PaymentRequest(descriptionPayment, idTypePayment, totalPayment);
        Payment response = paymentService.savePayment(request);
        assertEquals(totalPayment, response.getTotalPayment());
        assertEquals(descriptionPayment, response.getDescriptionPayment());
        assertEquals(idTypePayment, response.getTypePayment().getIdTypePayment());
    }

    @Test
    public void savePaymentTest_whenTypePaymentDoesNotExists(){
        Long idTypePayment = 10L;
        Double totalPayment = 500.0;
        String descriptionPayment = "No observations";
        PaymentRequest request = new PaymentRequest(descriptionPayment, idTypePayment, totalPayment);
        assertThrows(DataIntegrityViolationException.class, () -> paymentService.savePayment(request) );
    }

    @Test
    public void findByIdTest_successful(){
        Long idPayment = 1000L, expectedIdTypePayment = 1000L;
        String expectedDescriptionPayment = "No observations";
        Double expectedTotalPayment = 100.0;
        Payment response = paymentService.findById(idPayment);
        assertEquals(expectedTotalPayment, response.getTotalPayment());
        assertEquals(expectedDescriptionPayment, response.getDescriptionPayment());
        assertEquals(expectedIdTypePayment, response.getTypePayment().getIdTypePayment());
    }

    @Test
    public void findByIdTest_whenPaymentDoesNotExists(){
        Long idPayment = 10L;
        assertThrows(NotFoundException.class, () -> paymentService.findById(idPayment));
    }

    @Test
    public void findByIdTest_whenPaymentIsAlreadyDeleted(){
        Long idPayment = 1001L;
        assertThrows(NotFoundException.class, () -> paymentService.findById(idPayment));
    }

    @Test
    public void cancelPaymentTest_successful(){
        Long idPayment = 1002L;
        assertTrue(paymentService.deletePayment(idPayment));
        assertTrue(paymentRepository.findById(idPayment).get().getDeleted());
    }

    @Test
    public void cancelPaymentTest_whenPaymentDoesNotExists(){
        Long idPayment = 101L;
        assertThrows(NotFoundException.class, () -> paymentService.deletePayment(idPayment));
    }

    @Test
    public void cancelPaymentTest_whenPaymentIsAlreadyDeleted(){
        Long idPayment = 1003L;
        assertThrows(NotFoundException.class, () -> paymentService.deletePayment(idPayment));
    }

}
