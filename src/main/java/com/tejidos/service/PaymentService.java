package com.tejidos.service;

import com.tejidos.persistence.entity.Payment;
import com.tejidos.presentation.dto.request.PaymentRequest;


public interface PaymentService {
    Payment findById(Long idPayment);
    Payment savePayment(PaymentRequest paymentRequest);
    Boolean deletePayment(Long idPayment);
}
