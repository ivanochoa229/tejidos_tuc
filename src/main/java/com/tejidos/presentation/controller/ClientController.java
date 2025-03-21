package com.tejidos.presentation.controller;

import com.tejidos.presentation.dto.request.ClientRequest;
import com.tejidos.presentation.dto.response.ClientResponse;
import com.tejidos.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    public final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll(){
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long idClient){
        return new ResponseEntity<>(clientService.findById(idClient), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveClient(@Valid @RequestBody ClientRequest clientRequest){
        return new ResponseEntity<>(clientService.saveClient(clientRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<ClientResponse> updateClient(@Valid @RequestBody ClientRequest clientRequest, @PathVariable Long idClient){
        return new ResponseEntity<>(clientService.updateClient(clientRequest,idClient), HttpStatus.OK);
    }

    @DeleteMapping("/{idClient}")
    public ResponseEntity<String> deleteClient(@PathVariable Long idClient){
        return new ResponseEntity<>(clientService.deleteClient(idClient), HttpStatus.OK);
    }

}
