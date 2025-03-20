package com.tejidos.presentation.dto.response;

import com.tejidos.presentation.dto.SaleLineDetail;

import java.util.List;

public record SaleResponse(Long idSale,
                           Double total,
                           String status,
                           List<String> items) {
}
