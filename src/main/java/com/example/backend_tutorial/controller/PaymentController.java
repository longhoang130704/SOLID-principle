package com.example.backend_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tutorial.factory.PaymentFactory;
import com.example.backend_tutorial.service.TypeOfPayments.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    
    private PaymentFactory paymentFactory;

    @Autowired
    public PaymentController(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    public void createPayment(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    // method: credit-card, paypal, momo, vnpay
    @PostMapping("/{method}")
    public String payWithChosenPayment(@PathVariable String  method, @RequestParam double amount) {
        PaymentService paymentService = paymentFactory.createPaymentService(method);
        paymentService.processPayment(amount); // sửa tham số truyền vào
        return "Pay With Cradit Card successfully!";
    }

}
