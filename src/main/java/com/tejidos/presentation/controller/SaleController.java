package com.tejidos.presentation.controller;

import com.tejidos.presentation.dto.request.SaleRequest;
import com.tejidos.presentation.dto.response.SaleResponse;
import com.tejidos.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<SaleResponse>> findAllSaleByClient(@PathVariable Long idClient){
        return new ResponseEntity<>(saleService.findAllByClient(idClient), HttpStatus.OK);
    }

    @GetMapping("/{idSale}")
    public ResponseEntity<SaleResponse> findById(@PathVariable Long idSale){
        return new ResponseEntity<>(saleService.findById(idSale), HttpStatus.OK);
    }

    @DeleteMapping("/{idSale}")
    public ResponseEntity<String> cancelSale(@PathVariable Long idSale){
        return new ResponseEntity<>(saleService.cancelSale(idSale), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveSale(@RequestBody SaleRequest saleRequest){
        return new ResponseEntity<>(saleService.saveSale(saleRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{idSale}")
    public ResponseEntity<String> finishSale(@PathVariable Long idSale){
        return new ResponseEntity<>(saleService.finishSale(idSale), HttpStatus.OK);
    }

}
