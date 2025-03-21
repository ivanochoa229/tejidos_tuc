package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Address;
import com.tejidos.persistence.entity.Client;
import com.tejidos.persistence.repository.AddressRepository;
import com.tejidos.presentation.dto.request.AddressRequest;
import com.tejidos.presentation.dto.response.AddressResponse;
import com.tejidos.service.AddressService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressResponse> findAllByClient(Long idClient) {
        return addressRepository.findAllByClientIdAndNotDeleted(idClient)
                .stream()
                .map(a -> new AddressResponse(a.getIdAddress(), a.getNumber(), a.getProvince(), a.getState(), a.getStreet()))
                .toList();
    }

    @Override
    public AddressResponse findById(Long idAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(idAddress);
        if(optionalAddress.isEmpty()){
            throw new NotFoundException("Address with id: " + idAddress + " not found");
        }
        Address address = optionalAddress.get();
        return new AddressResponse(address.getIdAddress(), address.getNumber(), address.getProvince(), address.getState(), address.getStreet());
    }

    @Override
    public AddressResponse updateAddress(AddressRequest addressRequest, Long idAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(idAddress);
        if(optionalAddress.isEmpty()){
            throw new NotFoundException("Address with id: " + idAddress + " not found");
        }
        Address address = optionalAddress.get();
        address.setNumber(addressRequest.number());
        address.setProvince(addressRequest.province());
        address.setState(addressRequest.state());
        address.setStreet(addressRequest.street());
        addressRepository.save(address);
        return new AddressResponse(address.getIdAddress(), address.getNumber(), address.getProvince(), address.getState(), addressRequest.street());
    }

    @Override
    public String saveAddress(AddressRequest addressRequest) {
        Address address = new Address(new Client(addressRequest.idClient()), addressRequest.number(), addressRequest.province(), addressRequest.state(), addressRequest.street());
        addressRepository.save(address);
        return "Address created successfully";
    }

    @Override
    public String deleteAddress(Long idAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(idAddress);
        if(optionalAddress.isEmpty()){
            throw new NotFoundException("Address with id: " + idAddress + " not found");
        }
        Address address = optionalAddress.get();
        address.setDeleted(true);
        addressRepository.save(address);
        return "Address deleted successfully";
    }
}
