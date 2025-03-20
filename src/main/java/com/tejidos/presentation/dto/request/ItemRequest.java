package com.tejidos.presentation.dto.request;

public record ItemRequest(String descriptionItem,
                          Double priceItem,
                          String unit,
                          String category,
                          Double quantity) {
}
