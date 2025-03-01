package com.example.backend_tutorial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_tutorial.model.PaymentOrder;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    public Optional<PaymentOrder> findOrderById(Long id);
}
