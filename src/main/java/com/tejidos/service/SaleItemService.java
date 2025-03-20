package com.tejidos.service;

import com.tejidos.presentation.dto.SaleItemDTO;

import java.util.List;

public interface SaleItemService {
    void saveAll(List<SaleItemDTO> salesItems);
}
