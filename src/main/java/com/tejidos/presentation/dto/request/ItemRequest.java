package com.tejidos.presentation.dto.request;

public record ItemRequest(String descriptionItem,
                          Double priceItem,
                          Long idUnit,
                          Long idCategory,
                          Double quantity) {
}
