package com.example.backend_tutorial.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_tutorial.model.PaymentOrder;
import com.example.backend_tutorial.repository.PaymentOrderRepository;

@Service
public class PaymentOrderService {

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    public PaymentOrder createPaymentOrder(PaymentOrder paymentOrder) {
        // Nếu cần thêm business logic trước khi lưu, thêm ở đây
        return paymentOrderRepository.save(paymentOrder);
    }

    public List<PaymentOrder> getAllPaymentOrders() {
        return paymentOrderRepository.findAll();
    }

    public PaymentOrder getPaymentOrderById(Long id) {
        return paymentOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PaymentOrder not found with id: " + id));
    }

    public PaymentOrder updatePaymentOrder(Long id, PaymentOrder paymentOrderDetails) {
        PaymentOrder existingOrder = paymentOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PaymentOrder not found with id: " + id));

        existingOrder.setCustomer(paymentOrderDetails.getCustomer());
        existingOrder.setAmount(paymentOrderDetails.getAmount());
        existingOrder.setPaymentMethod(paymentOrderDetails.getPaymentMethod());
        existingOrder.setPaymentStatus(paymentOrderDetails.getPaymentStatus());

        return paymentOrderRepository.save(existingOrder);
    }

    public void deletePaymentOrder(Long id) {
        PaymentOrder existingOrder = paymentOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PaymentOrder not found with id: " + id));
        paymentOrderRepository.delete(existingOrder);
    }
}