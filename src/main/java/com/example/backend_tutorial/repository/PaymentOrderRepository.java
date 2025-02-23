package com.example.backend_tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_tutorial.model.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    
}
