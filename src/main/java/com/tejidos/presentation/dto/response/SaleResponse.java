package com.tejidos.presentation.dto.response;

import java.util.List;

public record SaleResponse(Long idSale,
                           Double total,
                           String status,
                           List<String> items) {
}
