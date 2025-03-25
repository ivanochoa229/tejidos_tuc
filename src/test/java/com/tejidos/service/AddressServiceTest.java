package com.tejidos.service;

import com.tejidos.persistence.entity.Address;
import com.tejidos.persistence.entity.Client;
import com.tejidos.persistence.repository.AddressRepository;
import com.tejidos.presentation.dto.request.AddressRequest;
import com.tejidos.presentation.dto.response.AddressResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.webjars.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/db_templates_test/AddressUseCaseTestInserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_templates_test/DeleteAllTemplateSqlTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void findByIdTest_successful(){
        Long idAddress = 1000L, expectedNumber = 123L;
        String  expectedStreet = "siempreviva", expectedState = "springfield",
                expectedProvince = "dakota";
        AddressResponse response = addressService.findById(idAddress);
        assertEquals(expectedStreet, response.street());
        assertEquals(expectedNumber, response.number());
        assertEquals(expectedProvince, response.province());
        assertEquals(expectedState, response.state());
    }

    @Test
    public void findByIdTest_whenAddressDoesNotExists(){
        Long idAddress = 100L;
        assertThrows(NotFoundException.class, () -> addressService.findById(idAddress));
    }

    @Test
    public void findAllByClientTest_successful(){
        Long idClient = 1001L, expectedNumber = 1234L;
        String  expectedStreet = "siempreviva", expectedState = "springfield",
                expectedProvince = "dakota";
        AddressResponse  response = addressService.findAllByClient(idClient).getFirst();
        assertEquals(expectedStreet, response.street());
        assertEquals(expectedNumber, response.number());
        assertEquals(expectedProvince, response.province());
        assertEquals(expectedState, response.state());
    }

    @Test
    public void findAllByClientTest_whenClientHasNoAddresses() {
        Long idClient = 1002L;
        List<AddressResponse> response = addressService.findAllByClient(idClient);
        assertTrue(response.isEmpty());
    }

    @Test
    public void findAllByClientTest_whenClientDoesNotExist() {
        Long idClient = 999L;
        List<AddressResponse> response = addressService.findAllByClient(idClient);
        assertTrue(response.isEmpty());
    }

    @Test
    public void findAllByClientTest_whenAllAddressesAreDeleted() {
        Long idClient = 1002L;
        List<AddressResponse> response = addressService.findAllByClient(idClient);
        assertTrue(response.isEmpty());
    }


    @Test
    public void deleteAddressTest_successful(){
        Long idAddress = 1003L;
        String expectedResponse = "Address deleted successfully" ;
        String response = addressService.deleteAddress(idAddress);
        assertEquals(expectedResponse, response);
        assertTrue(addressRepository.findById(idAddress).get().getDeleted());
    }

    @Test
    public void deleteAddressTest_whenAddressDoesNotExists(){
        Long idAddress = 100L;
        assertThrows(NotFoundException.class, () -> addressService.deleteAddress(idAddress));
    }

    @Test
    public void saveAddressTest_successful(){
        Long idClient = 1004L, number = 50L;
        String state = "springfield", province = "dakota", street = "siempreviva", expectedResponse = "Address created successfully";
        Client client = new Client(idClient);
        AddressRequest addressRequest = new AddressRequest(number, province, state, idClient, street);
        String response = addressService.saveAddress(addressRequest);
        assertEquals(expectedResponse, response);
        Address address = addressRepository.findByNumber(number);
        assertEquals(state, address.getState());
        assertEquals(province, address.getProvince());
        assertEquals( street, address.getStreet());
    }

    @Test
    public void saveAddressTest_whenClientDoesNotExists(){
        Long idClient = 544L, number = 100L;
        String state = "springfield", province = "dakota", street = "siempreviva", expectedResponse = "Address created successfully";
        Client client = new Client(idClient);
        AddressRequest addressRequest = new AddressRequest(number, province, state, idClient, street);
        assertThrows(DataIntegrityViolationException.class, () -> addressService.saveAddress(addressRequest));
    }

    @Test
    public void saveAddressTest_whenAddressNumberAndStreetAndProvinceAndStateAlreadyExists(){
        Long idClient = 1004L, number = 12346L;
        String state = "springfield", province = "dakota", street = "siempreviva";
        Client client = new Client(idClient);
        AddressRequest addressRequest = new AddressRequest(number, province, state, idClient, street);
        assertThrows(DataIntegrityViolationException.class, () -> addressService.saveAddress(addressRequest));
    }

    @Test
    public void updateAddressTest_successful(){
        Long idAddress = 1004L, newNumber = 10080L;
        String newState = "Las Talitas", newProvince = "Tucuman", newStreet = "Rondeau";
        Address address = addressRepository.findById(idAddress).orElseThrow();
        AddressRequest request = new AddressRequest(newNumber, newProvince,newState, address.getClient().getIdClient(), newStreet);
        AddressResponse response = addressService.updateAddress(request, idAddress);
        assertEquals(newStreet, response.street());
        assertEquals(newProvince, response.province());
        assertEquals(newNumber, response.number());
    }

    @Test
    public void updateAddressTest_whenAddressDoesNotExist(){
        Long idAddress = 100L;
        Long idClient = 1004L, number = 50L;
        String state = "springfield", province = "dakota", street = "siempreviva", expectedResponse = "Address created successfully";
        Client client = new Client(idClient);
        AddressRequest addressRequest = new AddressRequest(number, province, state, idClient, street);
        assertThrows(NotFoundException.class, () -> addressService.updateAddress(addressRequest, idAddress));
    }

    @Test
    public void updateAddressTest_whenAddressNumberAndStreetAndProvinceAndStateAlreadyExists(){
        Long idAddress = 1005L, newNumber = 10080L;
        String newState = "Las Talitas", newProvince = "Tucuman", newStreet = "Rondeau";
        Address address = addressRepository.findById(idAddress).orElseThrow();
        AddressRequest request = new AddressRequest(newNumber, newProvince,newState, address.getClient().getIdClient(), newStreet);
        assertThrows(DataIntegrityViolationException.class, () -> addressService.updateAddress(request, idAddress));
    }

}
