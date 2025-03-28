package com.tejidos.service.impl;

import com.tejidos.exception.CreationException;
import com.tejidos.persistence.entity.Client;
import com.tejidos.persistence.repository.ClientRepository;
import com.tejidos.presentation.dto.request.ClientRequest;
import com.tejidos.presentation.dto.response.ClientResponse;
import com.tejidos.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public String saveClient(ClientRequest clientRequest) {
        Optional<Client> optionalClient = clientRepository.findByDni(clientRequest.dni());
        if (optionalClient.isPresent()){
            throw new CreationException("The client with the dni: " + clientRequest.dni() + " is already created.");
        }
        Client client = new Client(clientRequest.name(), clientRequest.lastname(), clientRequest.dni(), clientRequest.phone());
        clientRepository.save(client);
        return "Client created successfully";
    }

    @Override
    public String deleteClient(Long idClient) {
        Optional<Client> optionalClient = clientRepository.findByIdClientAndDeletedFalse(idClient);
        if(optionalClient.isEmpty()){
            throw new NotFoundException("Client with id: " +  idClient + " not found.");
        }
        Client client = optionalClient.get();
        client.setDeleted(true);
        clientRepository.save(client);
        return "Client deleted successfully";
    }

    @Override
    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientServiceImpl::getClientResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ClientResponse findById(Long idClient) {
        Optional<Client> optionalClient = clientRepository.findByIdClientAndDeletedFalse(idClient);
        if(optionalClient.isEmpty()){
            throw new NotFoundException("Client with id: " +  idClient + " not found.");
        }
        return getClientResponse(optionalClient.get());
    }

    @Override
    @Transactional
    public ClientResponse updateClient(ClientRequest clientRequest, Long idClient) {
        Optional<Client> optionalClient = clientRepository.findByIdClientAndDeletedFalse(idClient);
        if(optionalClient.isEmpty()){
            throw new NotFoundException("Client with id: " +  idClient + " not found.");
        }
        Client client = optionalClient.get();
        client.setName(clientRequest.name() );
        client.setLastname(clientRequest.lastname());
        client.setDni(clientRequest.dni());
        client.setPhone(clientRequest.phone());
        clientRepository.save(client);
        return getClientResponse(client);
    }

    private static ClientResponse getClientResponse(Client c) {
        return new ClientResponse(c.getIdClient(), c.getName(), c.getLastname(), c.getDni(), c.getPhone(),
                c.getAddresses().stream().map(a -> a.getProvince()
                                .concat(", ")
                                .concat(a.getState())
                                .concat(": ")
                                .concat(a.getStreet())
                                .concat(" ").concat(a.getNumber().toString())
                        )
                        .collect(Collectors.toList()));
    }
}
