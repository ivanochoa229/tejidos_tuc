package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Payment;
import com.tejidos.persistence.entity.TypePayment;
import com.tejidos.persistence.repository.PaymentRepository;
import com.tejidos.presentation.dto.request.PaymentRequest;
import com.tejidos.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Payment findById(Long idPayment) {
        return paymentRepository.findByIdPaymentAndDeletedFalse(idPayment)
                .orElseThrow(() -> new NotFoundException("Payment with id: " + idPayment + " not found."));
    }

    @Override
    @Transactional
    public Payment savePayment(PaymentRequest paymentRequest) {
        TypePayment typePayment = new TypePayment(paymentRequest.idTypePayment());
        Payment payment = new Payment(paymentRequest.totalPayment(), paymentRequest.descriptionPayment(), typePayment);
        return paymentRepository.save(payment);
    }

    @Override
    public Boolean deletePayment(Long idPayment) {
        Payment payment = paymentRepository.findByIdPaymentAndDeletedFalse(idPayment)
                .orElseThrow(() -> new NotFoundException("Payment with id: " + idPayment + " not found."));
        payment.setDeleted(true);
        paymentRepository.save(payment);
        return true;
    }
}
