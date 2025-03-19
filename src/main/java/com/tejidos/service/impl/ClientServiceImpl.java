package com.tejidos.service.impl;

import com.tejidos.persistence.repository.ClientRepository;
import com.tejidos.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Boolean clientExitsAndIsNotDeleted(Long idClient) {
        return clientRepository.clientExistsAndIsNotDeleted(idClient).isPresent();
    }
}
