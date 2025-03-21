package com.tejidos.presentation.dto.request;

import com.tejidos.presentation.dto.SaleLineDetail;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaleRequest(
        @NotNull(message = "idClient cannot be null")
        @Min(value = 1, message = "idClient must be greater than 0")
        Long idClient,
        @NotEmpty(message = "SaleLineDetails must have at least one element")
        List<SaleLineDetail> saleLineDetails) {
}
