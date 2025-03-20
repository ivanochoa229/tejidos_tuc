package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Address;
import com.tejidos.persistence.entity.Client;
import com.tejidos.persistence.repository.AddressRepository;
import com.tejidos.presentation.dto.request.AddressRequest;
import com.tejidos.presentation.dto.response.AddressResponse;
import com.tejidos.service.AddressService;
import com.tejidos.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ClientService clientService;

    public AddressServiceImpl(AddressRepository addressRepository, ClientService clientService) {
        this.addressRepository = addressRepository;
        this.clientService = clientService;
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
            throw new RuntimeException();
        }
        Address address = optionalAddress.get();
        return new AddressResponse(address.getIdAddress(), address.getNumber(), address.getProvince(), address.getState(), address.getStreet());
    }

    @Override
    public AddressResponse updateAddress(AddressRequest addressRequest, Long idAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(idAddress);
        if(optionalAddress.isEmpty()){
            throw new RuntimeException();
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
        if(!clientService.clientExitsAndIsNotDeleted(addressRequest.idClient())){
            throw new RuntimeException();
        }
        addressRepository.save(new Address(new Client(addressRequest.idClient()), addressRequest.number(), addressRequest.province(), addressRequest.state(), addressRequest.street()));
        return "Address created successfully";
    }

    @Override
    public String deleteAddress(Long idAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(idAddress);
        if(optionalAddress.isEmpty()){
            throw new RuntimeException();
        }
        Address address = optionalAddress.get();
        address.setDeleted(true);
        addressRepository.save(address);
        return "Address deleted successfully";
    }
}
