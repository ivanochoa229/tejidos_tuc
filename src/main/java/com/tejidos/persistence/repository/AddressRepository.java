package com.tejidos.persistence.repository;

import com.tejidos.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.client.idClient = :clientId AND a.deleted = false")
    List<Address> findAllByClientIdAndNotDeleted(@Param("clientId") Long clientId);
    Address findByNumber(Long number);
    boolean existsByStreetAndNumberAndStateAndProvince(
            String street,
            Long number,
            String state,
            String province
    );
}
