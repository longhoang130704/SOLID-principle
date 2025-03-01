package com.example.backend_tutorial.service.TypeOfPayments;

import org.springframework.stereotype.Service;

import com.example.backend_tutorial.integration.momo.config.MoMoEnvironment;
import com.example.backend_tutorial.integration.momo.enums.RequestType;
import com.example.backend_tutorial.integration.momo.models.PaymentResponse;
import com.example.backend_tutorial.integration.momo.processor.CreateOrderMoMo;
import com.example.backend_tutorial.integration.momo.shared.exception.MoMoException;

@Service("momo")
public class MomoPaymentService implements PaymentService {

    private MoMoEnvironment environment;

    @Override
    public PaymentResponse processPayment(
            String orderId,
            String requestId,
            String amount,
            String orderInfo,
            String returnURL,
            String notifyURL,
            String extraData) throws MoMoException {
        System.out.println("Call MOMO repository");
        try {
            environment = MoMoEnvironment.selectEnv("dev");
            return CreateOrderMoMo.process(environment, orderId, requestId, amount, orderInfo, returnURL, notifyURL,
                    extraData, RequestType.CAPTURE_WALLET, true);
        } catch (Exception e) {
            System.out.println("Loi o momo payment service");
            throw new MoMoException("Lỗi khi tạo đơn hàng MoMo: " + e.getMessage());
        }
    }

    public void display() {
        System.out.println("Call MoMo Servie");
    }
}
