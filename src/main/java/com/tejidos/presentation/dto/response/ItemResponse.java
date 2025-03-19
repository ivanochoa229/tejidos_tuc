package com.tejidos.presentation.dto.response;

public record ItemResponse(Long idItem,
                           String descriptionItem,
                           Double priceItem,
                           String unit,
                           String category,
                           Double quantity) {
}
