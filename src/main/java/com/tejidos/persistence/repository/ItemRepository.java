package com.tejidos.persistence.repository;

import com.tejidos.persistence.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDeletedFalse();
    Optional<Item> findByIdAndDeletedFalse(Long idItem);
}
