package com.tejidos.presentation.dto.request;

public record PaymentRequest(String descriptionPayment, Long idTypePayment, Double totalPayment) {
}
