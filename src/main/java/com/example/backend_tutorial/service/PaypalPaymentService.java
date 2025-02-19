package com.example.backend_tutorial.service;

import org.springframework.stereotype.Service;

@Service("paypal")
public class PaypalPaymentService implements PaymentService {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Call Paypal repository");
        System.out.println("Paypal Service excute successfully! + " + amount);
    }
}
