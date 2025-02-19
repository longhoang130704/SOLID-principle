package com.example.backend_tutorial.factory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.backend_tutorial.service.PaymentService;

@Component
public class PaymentFactory {

    private final Map<String, PaymentService> paymentService;

    @Autowired
    public PaymentFactory(Map<String, PaymentService> paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentService createPaymentService(String type) {
        return paymentService.get(type);
    }
}
