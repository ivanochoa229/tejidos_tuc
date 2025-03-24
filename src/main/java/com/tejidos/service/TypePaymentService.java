package com.tejidos.service;

import com.tejidos.presentation.dto.response.TypePaymentResponse;

import java.util.List;

public interface TypePaymentService {
    List<TypePaymentResponse> findAll();
    TypePaymentResponse findById(Long idTypePayment);
}
