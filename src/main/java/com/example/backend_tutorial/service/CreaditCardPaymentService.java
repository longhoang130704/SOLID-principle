package com.example.backend_tutorial.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CreaditCardPaymentService implements PaymentService {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Call Creadit Card repository");
        System.out.println("Creadit Card Service excute successfully! + " + amount);
    }
}
