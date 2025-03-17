package com.tejidos.persistence.repository;

import com.tejidos.persistence.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
