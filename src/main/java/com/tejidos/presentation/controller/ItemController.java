package com.tejidos.presentation.controller;

import com.tejidos.presentation.dto.request.ItemRequest;
import com.tejidos.presentation.dto.response.ItemResponse;
import com.tejidos.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAll(){
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<ItemResponse> findById(@PathVariable Long idItem){
        return new ResponseEntity<>(itemService.findById(idItem), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveItem(@RequestBody ItemRequest itemRequest){
        return new ResponseEntity<>(itemService.saveItem(itemRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{idItem}")
    public ResponseEntity<ItemResponse> updateItem(@RequestBody ItemRequest itemRequest, @PathVariable Long idItem){
        return new ResponseEntity<>(itemService.updateItem(itemRequest, idItem), HttpStatus.OK);
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<String> deleteItem(@PathVariable Long idItem){
        return new ResponseEntity<>(itemService.deleteItem(idItem), HttpStatus.OK);
    }
}
