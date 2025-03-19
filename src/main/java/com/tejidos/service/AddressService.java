package com.tejidos.service;

import com.tejidos.presentation.dto.request.AddressRequest;
import com.tejidos.presentation.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> findAllByClient(Long idClient);
    AddressResponse findById(Long idAddress);
    AddressResponse updateAddress(AddressRequest addressRequest, Long idAddress);
    String saveAddress(AddressRequest addressRequest);
    String deleteAddress(Long idAddress);
}
