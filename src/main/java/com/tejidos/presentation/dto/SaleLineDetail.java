package com.tejidos.presentation.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SaleLineDetail(
        @NotNull(message = "idClient cannot be null")
        @Min(value = 1, message = "idClient must be greater than 0")
        Long itemId,
        @NotNull(message = "Quantity cannot be null")
        @DecimalMin(value = "0.01", message = "Quantity must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Quantity must have up to 10 integer digits and 2 decimal digits")
        Double quantity) {
}
