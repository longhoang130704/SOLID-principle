package com.example.backend_tutorial.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tutorial.dto.ApiRequest.MomoPaymentRequest;
import com.example.backend_tutorial.factory.PaymentFactory;
import com.example.backend_tutorial.integration.momo.models.PaymentResponse;
import com.example.backend_tutorial.integration.momo.shared.exception.MoMoException;
import com.example.backend_tutorial.service.TypeOfPayments.PaymentService;
import com.example.backend_tutorial.util.momo.MapMomoCallBack;
import com.fasterxml.jackson.databind.JsonNode;

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
    public String payWithChosenPayment(@PathVariable String method, @RequestParam double amount) {
        PaymentService paymentService = paymentFactory.createPaymentService(method);
        paymentService.display(); // sửa tham số truyền vào
        return "Pay With Cradit Card successfully!";
    }

    @PostMapping("/{method}/create")
    public PaymentResponse creatPayment(
            @PathVariable String method,
            @RequestBody MomoPaymentRequest momoPaymentRequest) throws MoMoException {

        PaymentService paymentService = paymentFactory.createPaymentService(method);
        // return paymentService.processPayment(orderId, requestId, amount, orderInfo,
        // returnURL, notifyURL, extraData);

        return paymentService.processPayment(
                momoPaymentRequest.getOrderId(),
                momoPaymentRequest.getRequestId(),
                momoPaymentRequest.getAmount(),
                momoPaymentRequest.getOrderInfo(),
                momoPaymentRequest.getReturnURL(),
                momoPaymentRequest.getNotifyURL(),
                momoPaymentRequest.getExtraData());
    }

    @GetMapping("/{method}/call-back")
    public String callBackPayment(@PathVariable String method, @RequestParam Map<String, String> params)
            throws RuntimeException {
        PaymentService paymentService = paymentFactory.createPaymentService(method);

        // System.out.println(params); // debug input

        try {
            JsonNode jsonParams = MapMomoCallBack.mapJsonNode(params);
            // MomoCallBackRequest momoCallBackRequest = MapMomoCallBack.map(jsonParams);

            // return momoCallBackRequest;
            return paymentService.processCallBack(jsonParams);
        } catch (Exception e) {
            System.out.println("Loi co the xay ra o trong process call back");
            throw new RuntimeException("Cannot map callback request in util momo" + e.getMessage());
        }
    }
}
