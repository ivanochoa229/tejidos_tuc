package com.tejidos.presentation.dto.request;

import com.tejidos.presentation.dto.SaleLineDetail;

import java.util.List;

public record SaleRequest(Double total, Long idClient, List<SaleLineDetail> saleLineDetails) {
}
