package com.tejidos.persistence.repository;

import com.tejidos.persistence.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByClientIdClient(Long idClient);
}
