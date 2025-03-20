package com.tejidos.service;

import com.tejidos.presentation.dto.request.ItemRequest;
import com.tejidos.presentation.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {
    List<ItemResponse> findAll();
    ItemResponse findById(Long idItem);
    ItemResponse updateItem(ItemRequest itemRequest, Long idItem);
    String deleteItem(Long idItem);
    String saveItem(ItemRequest itemRequest);
}
