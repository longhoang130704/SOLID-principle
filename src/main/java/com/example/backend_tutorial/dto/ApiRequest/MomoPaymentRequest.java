package com.example.backend_tutorial.dto.ApiRequest;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MomoPaymentRequest {

    private String orderId;
    private String requestId;
    private String amount;
    private String orderInfo;
    private String returnURL;
    private String notifyURL;
    private String extraData;
}
