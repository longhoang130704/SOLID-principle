package com.example.backend_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tutorial.factory.PaymentFactory;
import com.example.backend_tutorial.service.CreaditCardPaymentService;
import com.example.backend_tutorial.service.MomoPaymentService;
import com.example.backend_tutorial.service.PaypalPaymentService;

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

    @PostMapping("/credit-card")
    public String payWithCreaditCard(@RequestParam double amount) {
        paymentFactory.setPaymentService(new CreaditCardPaymentService());
        paymentFactory.excutePayment(amount);
        return "Pay With Cradit Card successfully!";
    }

    @PostMapping("/paypal")
    public String payWithPaypal(@RequestParam double amount) {
        paymentFactory.setPaymentService(new PaypalPaymentService());
        paymentFactory.excutePayment(amount);
        return "Pay With Paypal successfully!";
    }

    @PostMapping("/momo")
    public String payWithMomo(@RequestParam double amount) {
        paymentFactory.setPaymentService(new MomoPaymentService());
        paymentFactory.excutePayment(amount);
        return "Pay With MOMO successfully!";
    }
}
