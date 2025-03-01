package com.example.backend_tutorial.util.momo;

import java.util.Map;

import com.example.backend_tutorial.dto.ApiRequest.MomoCallBackRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapMomoCallBack {
    public static MomoCallBackRequest map(JsonNode params) {
        MomoCallBackRequest momoCallBackRequest = new MomoCallBackRequest();

        momoCallBackRequest.setPartnerCode(params.get("partnerCode").asText());
        momoCallBackRequest.setOrderId(params.get("orderId").asText());
        momoCallBackRequest.setRequestId(params.get("requestId").asText());
        momoCallBackRequest.setAmount(params.has("amount") ? params.get("amount").asLong() : 0L);
        momoCallBackRequest.setOrderInfo(params.get("orderInfo").asText());
        momoCallBackRequest.setOrderType(params.get("orderType").asText());
        momoCallBackRequest.setTransId(params.has("transId") ? params.get("transId").asLong() : 0L);
        momoCallBackRequest.setResultCode(params.has("resultCode") ? params.get("resultCode").asInt() : 0);
        momoCallBackRequest.setMessage(params.get("message").asText());
        momoCallBackRequest.setPayType(params.get("payType").asText());
        momoCallBackRequest.setResponseTime(params.has("responseTime") ? params.get("responseTime").asLong() : 0L);
        momoCallBackRequest.setExtraData(params.get("extraData").asText());
        momoCallBackRequest.setSignature(params.get("signature").asText());

        return momoCallBackRequest;
    }

    public static JsonNode mapJsonNode(Map<String, String> params) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonParams = objectMapper.convertValue(params, JsonNode.class);
        return jsonParams;
    }
}
