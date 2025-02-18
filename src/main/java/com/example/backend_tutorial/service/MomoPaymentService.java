package com.example.backend_tutorial.service;

import org.springframework.stereotype.Service;

@Service
public class MomoPaymentService implements PaymentService {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Call MOMO repository");
        System.out.println("Payment with MOMO successfully! + " + amount);
    }
}
