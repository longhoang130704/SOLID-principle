package com.example.backend_tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tutorial.model.PaymentOrder;
import com.example.backend_tutorial.service.PaymentOrderService;

@RestController
@RequestMapping("/api/payment-order")
public class PaymentOrderController {

    @Autowired
    private PaymentOrderService paymentOrderService;

    @PostMapping
    public PaymentOrder createPaymentOrder(@RequestBody PaymentOrder paymentOrder) {
        return paymentOrderService.createPaymentOrder(paymentOrder);
    }

    @GetMapping
    public List<PaymentOrder> getAllPaymentOrders() {
        return paymentOrderService.getAllPaymentOrders();
    }

    @GetMapping("/{id}")
    public PaymentOrder getPaymentOrderById(@PathVariable Long id) {
        return paymentOrderService.getPaymentOrderById(id);
    }

    @PutMapping("/{id}")
    public PaymentOrder updatePaymentOrder(@PathVariable Long id, @RequestBody PaymentOrder paymentOrderDetails) {
        return paymentOrderService.updatePaymentOrder(id, paymentOrderDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentOrder(@PathVariable Long id) {
        paymentOrderService.deletePaymentOrder(id);
    }
}
