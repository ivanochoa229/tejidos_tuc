package com.tejidos.persistence.repository;

import com.tejidos.persistence.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDeletedFalse();
    Optional<Item> findByIdItemAndDeletedFalse(Long idItem);
    @Query("SELECT i.priceItem FROM Item i WHERE i.idItem = :idItem")
    Optional<Double> findPriceById(@Param("idItem") Long idItem);
}
