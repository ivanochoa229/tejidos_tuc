package com.tejidos.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressRequest(
        @NotNull(message = "Number cannot be null")
        @Min(value = 1, message = "Number must be greater than 0")
        Long number,
        @NotBlank(message = "Province cannot be blank")
        @Size(min = 3, max = 50, message = "Province must be between 3 and 50 characters")
        String province,
        @NotBlank(message = "State cannot be blank")
        @Size(min = 3, max = 50, message = "State must be between 3 and 50 characters")
        String state,
        @NotNull(message = "idClient cannot be null")
        @Min(value = 1, message = "idClient must be greater than 0")
        Long idClient,
        @NotBlank(message = "Street cannot be blank")
        @Size(min = 3, max = 50, message = "Street must be between 3 and 50 characters")
        String street) {
}
