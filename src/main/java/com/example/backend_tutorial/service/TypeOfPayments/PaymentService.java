package com.example.backend_tutorial.service.TypeOfPayments;

import org.springframework.stereotype.Component;

import com.example.backend_tutorial.integration.momo.models.PaymentResponse;
import com.example.backend_tutorial.integration.momo.shared.exception.MoMoException;

@Component
public interface PaymentService {
    public PaymentResponse processPayment(
            String orderId,
            String requestId,
            String amount,
            String orderInfo,
            String returnURL,
            String notifyURL,
            String extraData) throws MoMoException;

    public void display();
}
