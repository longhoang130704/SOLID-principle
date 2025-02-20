package com.example.backend_tutorial.service.TypeOfPayments;

import org.springframework.stereotype.Service;

@Service("vnpay")
public class VnPayPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("Vn Pay is called");
    }
    
}
