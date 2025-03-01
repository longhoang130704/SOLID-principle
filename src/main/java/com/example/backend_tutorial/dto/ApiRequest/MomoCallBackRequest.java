package com.example.backend_tutorial.dto.ApiRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MomoCallBackRequest {
    private String partnerCode;
    private String orderId;
    private String requestId;
    private Long amount;
    private String orderInfo;
    private String orderType;
    private Long transId;
    private int resultCode;
    private String message;
    private String payType;
    private Long responseTime;
    private String extraData;
    private String signature;

}
