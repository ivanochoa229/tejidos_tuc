package com.tejidos.presentation.dto.request;

import jakarta.validation.constraints.*;

public record ItemRequest(
        @NotBlank(message = "Description cannot be blank")
        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Description must contain only letters and spaces")
        String descriptionItem,
        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Price must have up to 1 integer digits and 2 decimal digits")
        Double priceItem,
        @NotNull(message = "idUnit cannot be null")
        @Min(value = 1, message = "idUnit must be greater than 0")
        Long idUnit,
        @NotNull(message = "idCategory cannot be null")
        @Min(value = 1, message = "idCategory must be greater than 0")
        Long idCategory,
        @NotNull(message = "Quantity cannot be null")
        @DecimalMin(value = "0.01", message = "Quantity must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Quantity must have up to 10 integer digits and 2 decimal digits")
        Double quantity) {
}
