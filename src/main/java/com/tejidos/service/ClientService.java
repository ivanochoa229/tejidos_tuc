package com.tejidos.service;

import com.tejidos.presentation.dto.request.ClientRequest;
import com.tejidos.presentation.dto.response.ClientResponse;

import java.util.List;

public interface ClientService {
    Boolean clientExitsAndIsNotDeleted(Long idClient);
    String saveClient(ClientRequest clientRequest);
    String deleteClient(Long idClient);
    List<ClientResponse> findAll();
    ClientResponse findById(Long idClient);
    ClientResponse updateClient(ClientRequest clientRequest, Long idClient);

}
