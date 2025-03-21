package com.tejidos.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,
        @NotBlank(message = "Lastname cannot be blank")
        @Size(min = 3, max = 50, message = "Lastname must be between 3 and 50 characters")
        String lastname,
        @NotBlank(message = "Dni cannot be blank")
        @Size(min = 7, max = 8, message = "Dni must be between 7 and 8 characters")
        @Pattern(regexp = "\\d+", message = "Dni must contain only digits")
        String dni,
        @NotBlank(message = "Phone cannot be blank")
        @Size(min = 10, max = 10, message = "Phone must be 10 characters")
        @Pattern(regexp = "\\d+", message = "Phone must contain only digits")
        String phone) {
}
