package com.example.backend_tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tutorial.model.PaymentDetail;
import com.example.backend_tutorial.service.PaymentDetailService;

@RestController
@RequestMapping("/api/payment-detail")
public class PaymentDetailController {
    @Autowired
    private PaymentDetailService paymentDetailService;

    @PostMapping
    public PaymentDetail creatPaymentDetail(@RequestBody PaymentDetail paymentDetailRequest) {
        return paymentDetailService.createPaymentDetail(paymentDetailRequest.getId(), paymentDetailRequest.getDetailData());
    }

    @GetMapping
    public List<PaymentDetail> getAllPaymentDetail() {
        return paymentDetailService.getAllPaymentDetail();
    }

    @GetMapping("/{paymentDetailId}")
    public PaymentDetail getPaymentDetailById(@PathVariable Long paymentDetailId) {
        return paymentDetailService.getPaymentDetailById(paymentDetailId);
    }

}
