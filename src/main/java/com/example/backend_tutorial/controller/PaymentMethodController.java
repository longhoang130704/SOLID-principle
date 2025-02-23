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

import com.example.backend_tutorial.model.PaymentMethod;
import com.example.backend_tutorial.service.PaymentMethodService;

@RestController
@RequestMapping("/api/payment-method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public List<PaymentMethod> getAll() {
        return paymentMethodService.getAllPaymentMethods();
    }

    @GetMapping("/{id}")
    public PaymentMethod getById(@PathVariable Long id) {
        return paymentMethodService.getPaymentMethodById(id);
    }

    @PostMapping
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.createPaymentMethod(paymentMethod);
    }

    @PutMapping("/{id}")
    public PaymentMethod update(@PathVariable Long id, @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.updatePaymentMethod(id, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return paymentMethodService.deletePaymentMethod(id);
    }
}
