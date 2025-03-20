package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Client;
import com.tejidos.persistence.entity.Item;
import com.tejidos.persistence.entity.Sale;
import com.tejidos.persistence.repository.SaleRepository;
import com.tejidos.presentation.dto.SaleItemDTO;
import com.tejidos.presentation.dto.SaleLineDetail;
import com.tejidos.presentation.dto.request.SaleRequest;
import com.tejidos.presentation.dto.response.SaleResponse;
import com.tejidos.service.ItemService;
import com.tejidos.service.SaleItemService;
import com.tejidos.service.SaleService;
import com.tejidos.utils.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;
    private final ItemService itemService;

    public SaleServiceImpl(SaleRepository saleRepository, SaleItemService saleItemService, ItemService itemService) {
        this.saleRepository = saleRepository;
        this.saleItemService = saleItemService;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public List<SaleResponse> findAllByClient(Long idClient) {
        return saleRepository.findByClientIdClient(idClient).stream()
                .map(s -> {
                    List<String> items = s.getSaleItems().stream().map(i -> i.getItem().getDescriptionItem()).collect(Collectors.toList());
                    return new SaleResponse(s.getIdSale(), s.getTotal(), s.getStatus().name(), items);
                })
                .collect(Collectors.toList());
    }

    @Override
    public SaleResponse findById(Long idSale) {
        Sale sale = getSale(idSale);
        return new SaleResponse(idSale, sale.getTotal(),
                    sale.getStatus().name(), sale.getSaleItems()
                    .stream()
                    .map(i -> i.getItem().getDescriptionItem()).collect(Collectors.toList()));
    }


    @Override
    public String cancelSale(Long idSale) {
        Sale sale = getSale(idSale);
        sale.setStatus(Status.CANCELLED);
        saleRepository.save(sale);
        return "Sale cancelled successfully";
    }

    @Override
    @Transactional
    public SaleResponse saveSale(SaleRequest saleRequest) {
        Sale sale = new Sale(new Client(saleRequest.idClient()),Status.PENDING_PAYMENT, 0.0 );
        sale = saleRepository.save(sale);


        List<SaleItemDTO> saleItems = new ArrayList<>();
        double total = 0.0;

        for (SaleLineDetail saleLineDetail : saleRequest.saleLineDetails()) {

            Item item = new Item(saleLineDetail.itemId());

            SaleItemDTO saleItem = new SaleItemDTO(sale.getIdSale(), item.getIdItem(), saleLineDetail.quantity(), item.getPriceItem(), item.getPriceItem() * saleLineDetail.quantity());

            saleItems.add(saleItem);
            total += saleItem.subtotal();
        }

        sale.setTotal(total);
        saleRepository.save(sale);

        saleItemService.saveAll(saleItems);

        return new SaleResponse(sale.getIdSale(), sale.getTotal(), sale.getStatus().name(), saleItems.stream().map( s-> s.idSale().toString()).toList());
    }


    private Sale getSale(Long idSale) {
        Optional<Sale> optionalSale = saleRepository.findById(idSale);
        if (optionalSale.isEmpty()){
            throw new RuntimeException();
        }
        return optionalSale.get();
    }

    private SaleResponse mapToSaleResponse(Sale sale) {
        List<String> items = sale.getSaleItems().stream()
                .map(saleItem -> saleItem.getItem().getDescriptionItem())
                .toList();
        return new SaleResponse(
                sale.getIdSale(),
                sale.getTotal(),
                sale.getStatus().name(),
                items
        );
    }

}
