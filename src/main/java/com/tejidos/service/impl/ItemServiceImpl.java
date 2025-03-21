package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Category;
import com.tejidos.persistence.entity.Item;
import com.tejidos.persistence.entity.Unit;
import com.tejidos.persistence.repository.ItemRepository;
import com.tejidos.presentation.dto.request.ItemRequest;
import com.tejidos.presentation.dto.response.ItemResponse;
import com.tejidos.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public List<ItemResponse> findAll() {
        return itemRepository.findByDeletedFalse().stream()
                .map(i ->
                        new ItemResponse(i.getIdItem(), i.getDescriptionItem(), i.getPriceItem(),
                                i.getUnit().getUnitName(), i.getCategory().getNameCategory(),
                                i.getQuantity()))
                .toList();
    }

    @Override
    @Transactional
    public ItemResponse findById(Long idItem) {
        Optional<Item> optionalItem = itemRepository.findByIdItemAndDeletedFalse(idItem);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Item with id: " + idItem + " not found");
        }
        Item item = optionalItem.get();
        return new ItemResponse(item.getIdItem(), item.getDescriptionItem(), item.getPriceItem(),
                item.getUnit().getUnitName(), item.getCategory().getNameCategory(), item.getQuantity());
    }

    @Override
    public ItemResponse updateItem(ItemRequest itemRequest, Long idItem) {
        Optional<Item> optionalItem = itemRepository.findByIdItemAndDeletedFalse(idItem);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Item with id: " + idItem + " not found");
        }
        Item item = optionalItem.get();
        Category category = new Category(itemRequest.idCategory());
        Unit unit = new Unit(itemRequest.idUnit());
        item.setPriceItem(itemRequest.priceItem());
        item.setDescriptionItem(itemRequest.descriptionItem());
        item.setQuantity(item.getQuantity());
        item.setUnit(unit);
        item.setCategory(category);
        itemRepository.save(item);
        return new ItemResponse(idItem, item.getDescriptionItem(), item.getPriceItem(), unit.getUnitName(), category.getNameCategory(), itemRequest.quantity());
    }

    @Override
    public String deleteItem(Long idItem) {
        Optional<Item> optionalItem = itemRepository.findByIdItemAndDeletedFalse(idItem);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Item with id: " + idItem + " not found");
        }
        Item item = optionalItem.get();
        item.setDeleted(true);
        return "Item deleted successfully";
    }

    @Override
    public String saveItem(ItemRequest itemRequest) {
        Unit unit = new Unit(itemRequest.idUnit());
        Category category = new Category(itemRequest.idCategory());
        Item item = new Item(category, itemRequest.descriptionItem(), itemRequest.priceItem(), itemRequest.quantity(), unit);
        itemRepository.save(item);
        return "Item created successfully";
    }

    @Override
    public Item findEntityById(Long idItem) {
        Optional<Item> optionalItem = itemRepository.findById(idItem);
        if(optionalItem.isEmpty()){
            throw new NotFoundException("Item with id: " + idItem + " not found");
        }
        return optionalItem.get();
    }
}
