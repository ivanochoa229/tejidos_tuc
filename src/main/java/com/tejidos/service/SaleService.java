package com.tejidos.service;

import com.tejidos.presentation.dto.request.SaleRequest;
import com.tejidos.presentation.dto.response.SaleResponse;

import java.util.List;

public interface SaleService {
    List<SaleResponse> findAllByClient(Long idClient);
    SaleResponse findById(Long idSale);
    String cancelSale(Long idSale);
    String saveSale(SaleRequest saleRequest);
    String finishSale(Long idSale);
}
