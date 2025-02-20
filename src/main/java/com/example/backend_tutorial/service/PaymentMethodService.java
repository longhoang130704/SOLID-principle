package com.example.backend_tutorial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.backend_tutorial.model.PaymentMethod;
import com.example.backend_tutorial.repository.PaymentMethodRepository;

@Service
public class PaymentMethodService {
    
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> getAllPaymentMethod() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findMethodById(id)
            .orElseThrow(() -> new RuntimeException("Not found PaymentMethod with id: " + id));
    }

    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        try {
            return paymentMethodRepository.save(paymentMethod);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error: Duplicate payment method name", e);
        }
    }
    
    public PaymentMethod updatePaymentMethod(Long id, PaymentMethod paymentMethodDetails) {
        PaymentMethod paymentMethod = paymentMethodRepository.findMethodById(id)
            .orElseThrow(() -> new RuntimeException("Not found PaymentMethod with id: " + id));
        
        paymentMethod.setName(paymentMethodDetails.getName());
        paymentMethod.setDescription(paymentMethodDetails.getDescription());
        
        try {
            return paymentMethodRepository.save(paymentMethod);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error: Duplicate payment method name", e);
        }
    }
    
    public void deletePaymentMethod(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findMethodById(id)
            .orElseThrow(() -> new RuntimeException("Not found PaymentMethod with id: " + id));
        paymentMethodRepository.delete(paymentMethod);
    }

}
