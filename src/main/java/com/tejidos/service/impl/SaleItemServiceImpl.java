package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Item;
import com.tejidos.persistence.entity.Sale;
import com.tejidos.persistence.entity.SaleItem;
import com.tejidos.persistence.repository.SaleItemRepository;
import com.tejidos.presentation.dto.SaleItemDTO;
import com.tejidos.service.SaleItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;

    public SaleItemServiceImpl(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    @Override
    @Transactional
    public void saveAll(List<SaleItemDTO> salesItems) {
        saleItemRepository.saveAll(salesItems
                .stream()
                .map(s -> new SaleItem(new Sale(s.idSale()), new Item(s.idItem()), s.quantity(), s.price(), s.subtotal())
                ).collect(Collectors.toList()));
    }
}
