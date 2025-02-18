package com.example.backend_tutorial.service;

import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void processPayment(double amount);
}
