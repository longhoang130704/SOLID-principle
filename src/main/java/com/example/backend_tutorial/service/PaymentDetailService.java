package com.example.backend_tutorial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_tutorial.model.PaymentDetail;
import com.example.backend_tutorial.model.PaymentOrder;
import com.example.backend_tutorial.repository.PaymentDetailRepository;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class PaymentDetailService {
    @Autowired
    private PaymentDetailRepository paymentDetailRepository;

    public List<PaymentDetail> getAllPaymentDetail() {
        return paymentDetailRepository.findAll();
    }

    public PaymentDetail getPaymentDetailById(Long id) {
        try {
            PaymentDetail paymentDetail = paymentDetailRepository.findPaymentDetailById(id)
                .orElseThrow(() -> new RuntimeException("Payment Detail Id Not Found: " + id));
            return paymentDetail;
        } catch (Exception e) {
            throw new RuntimeException("Internal server Error");
        }
    }

    public PaymentDetail createPaymentDetail(Long payment_id, JsonNode detailData) {
        
        PaymentDetail savedPaymentDetail = convertToPaymentDetail(payment_id, detailData);

        try {
            return paymentDetailRepository.save(savedPaymentDetail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Internal Server Error! ");
        }
    }

    private PaymentDetail convertToPaymentDetail(Long payment_id, JsonNode detailData) {
        PaymentDetail paymentDetail = new PaymentDetail();
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId(payment_id);  // Đảm bảo PaymentOrder có setter cho id

        paymentDetail.setPayment_id(paymentOrder);
        paymentDetail.setDetailData(detailData);
        return paymentDetail; 
    }

}
