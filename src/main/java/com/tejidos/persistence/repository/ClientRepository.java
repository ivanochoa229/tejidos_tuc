package com.tejidos.persistence.repository;

import com.tejidos.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.idClient = :idClient AND c.deleted = false")
    Optional<Client> clientExistsAndIsNotDeleted(@Param("idClient") Long idClient);
    Optional<Client> findByIdClientAndDeletedFalse(Long idClient);
    Optional<Client> findByDni(String dni);
}
