package com.tejidos.presentation.controller;

import com.tejidos.presentation.dto.request.AddressRequest;
import com.tejidos.presentation.dto.response.AddressResponse;
import com.tejidos.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<AddressResponse>> findAllByIdClient(@PathVariable Long idClient){
        return new ResponseEntity<>(addressService.findAllByClient(idClient), HttpStatus.OK);
    }

    @GetMapping("/{idAddress}")
    public ResponseEntity<AddressResponse> findById(@PathVariable Long idAddress){
        return new ResponseEntity<>(addressService.findById(idAddress), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveAddress(@RequestBody AddressRequest addressRequest){
        return new ResponseEntity<>(addressService.saveAddress(addressRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{idAddress}")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable Long idAddress){
        return new ResponseEntity<>(addressService.updateAddress(addressRequest, idAddress), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idAddress}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long idAddress){
        return new ResponseEntity<>(addressService.deleteAddress(idAddress), HttpStatus.OK);
    }

}
