package com.tejidos.service;

import com.tejidos.exception.CreationException;
import com.tejidos.persistence.repository.ClientRepository;
import com.tejidos.presentation.dto.request.ClientRequest;
import com.tejidos.presentation.dto.response.ClientResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/ClientUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void saveClientTest_successful(){
        String name = "Alejandro", lastname = "Magno", dni = "15942612", phone = "456789123", expectedResponse = "Client created successfully";
        ClientRequest request = new ClientRequest(name, lastname, dni, phone);
        String response = clientService.saveClient(request);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void saveClientTest_whenClientWithDniAlreadyExists(){
        String name = "Juan", lastname = "Gimenez", dni = "45815204", phone = "1056954871";
        ClientRequest request = new ClientRequest(name, lastname, dni, phone);
        assertThrows(CreationException.class, ()  -> clientService.saveClient(request));
    }

    @Test
    public void deleteClientTest_successful(){
        Long idClient = 1001L;
        String expectedResponse = "Client deleted successfully";
        String response = clientService.deleteClient(idClient);
        assertEquals(expectedResponse, response);
        assertTrue(clientRepository.findById(idClient).get().getDeleted());
    }

    @Test
    public void deleteClientTest_whenClientDoesNotExists(){
        Long idClient = 101L;
        assertThrows(NotFoundException.class, ()  -> clientService.deleteClient(idClient));
    }

    @Test
    public void deleteClientTest_whenClientIsAlreadyDeleted(){
        Long idClient = 1002L;
        assertThrows(NotFoundException.class, ()  -> clientService.deleteClient(idClient));
    }

    @Test
    public void findByIdTest_successful(){
        Long idClient = 1003L;
        String expectedName = "Nestor", expectedLastname = "Ortigoza", expectedDni = "40815444", expectedPhone = "1856988871";
        ClientResponse response = clientService.findById(idClient);
        assertEquals(expectedName, response.name());
        assertEquals(expectedLastname, response.lastname());
        assertEquals(expectedDni, response.dni());
        assertEquals(expectedPhone, response.phone());
    }

    @Test
    public void findByIdTest_whenClientDoesNotExists(){
        Long idClient = 102L;
        assertThrows(NotFoundException.class, ()  -> clientService.findById(idClient));
    }

    @Test
    public void findByIdTest_whenClientIsAlreadyDeleted(){
        Long idClient = 1004L;
        assertThrows(NotFoundException.class, ()  -> clientService.findById(idClient));
    }

    @Test
    public void updateClientTest_successful(){
        Long idClient = 1005L;
        String expectedName = "Luis", expectedLastname = "Sera", expectedDni = "90815444", expectedPhone = "1856900071";
        ClientRequest request = new ClientRequest(expectedName, expectedLastname, expectedDni, expectedPhone);
        ClientResponse response = clientService.updateClient(request, idClient);
        assertEquals(expectedName, response.name());
        assertEquals(expectedLastname, response.lastname());
        assertEquals(expectedDni, response.dni());
        assertEquals(expectedPhone, response.phone());
    }

    @Test
    public void updateClientTest_whenDniIsAlreadyUsed(){
        Long idClient = 1006L;
        String expectedName = "Andres", expectedLastname = "Vilar", expectedDni = "66815405", expectedPhone = "1856977771";
        ClientRequest request = new ClientRequest(expectedName, expectedLastname, expectedDni, expectedPhone);
        assertThrows(DataIntegrityViolationException.class, () -> clientService.updateClient(request, idClient));
    }

    @Test
    public void updateClientTest_whenClientDoesNotExists(){
        Long idClient = 106L;
        String expectedName = "Andres", expectedLastname = "Villagra", expectedDni = "10665404", expectedPhone = "1856977771";
        ClientRequest request = new ClientRequest(expectedName, expectedLastname, expectedDni, expectedPhone);
        assertThrows(NotFoundException.class, ()-> clientService.updateClient(request, idClient));
    }

    @Test
    public void updateClientTest_whenClientIsAlreadyDeleted(){
        Long idClient = 1007L;
        String expectedName = "Sebastian", expectedLastname = "Palacios", expectedDni = "10665404", expectedPhone = "1856977771";
        ClientRequest request = new ClientRequest(expectedName, expectedLastname, expectedDni, expectedPhone);
        assertThrows(NotFoundException.class, ()-> clientService.updateClient(request, idClient));
    }


}
