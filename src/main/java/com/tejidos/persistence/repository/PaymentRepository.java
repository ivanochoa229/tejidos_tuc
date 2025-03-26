package com.tejidos.persistence.repository;

import com.tejidos.persistence.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByIdPaymentAndDeletedFalse(Long idPayment);
}
