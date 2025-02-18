package com.example.backend_tutorial.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.backend_tutorial.service.PaymentService;

@Component
public class PaymentFactory {

    private PaymentService paymentService;

    @Autowired
    public PaymentFactory(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void excutePayment(double amount) {
        paymentService.processPayment(amount);
    }
}
