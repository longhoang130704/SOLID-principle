package com.example.backend_tutorial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_tutorial.model.PaymentDetail;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
    public Optional<PaymentDetail> findPaymentDetailById(Long id);
}
