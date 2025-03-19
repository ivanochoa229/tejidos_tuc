package com.tejidos.presentation.dto;

public record SaleItemDTO(Long idItem,
                          Long idSale,
                          Double quantity,
                          Double price,
                          Double subtotal) {
}
