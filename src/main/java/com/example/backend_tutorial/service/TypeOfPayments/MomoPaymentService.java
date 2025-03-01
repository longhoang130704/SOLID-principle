package com.example.backend_tutorial.service.TypeOfPayments;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend_tutorial.dto.ApiRequest.MomoCallBackRequest;
import com.example.backend_tutorial.integration.momo.config.MoMoEnvironment;
import com.example.backend_tutorial.integration.momo.enums.RequestType;
import com.example.backend_tutorial.integration.momo.models.PaymentResponse;
import com.example.backend_tutorial.integration.momo.processor.CreateOrderMoMo;
import com.example.backend_tutorial.integration.momo.shared.exception.MoMoException;
import com.example.backend_tutorial.model.Customer;
import com.example.backend_tutorial.model.PaymentDetail;
import com.example.backend_tutorial.model.PaymentMethod;
import com.example.backend_tutorial.model.PaymentOrder;
import com.example.backend_tutorial.model.PaymentStatus;
import com.example.backend_tutorial.repository.CustomerRepository;
import com.example.backend_tutorial.repository.PaymentDetailRepository;
import com.example.backend_tutorial.repository.PaymentMethodRepository;
import com.example.backend_tutorial.repository.PaymentOrderRepository;
import com.example.backend_tutorial.util.momo.MapMomoCallBack;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.EntityNotFoundException;

@Service("momo")
public class MomoPaymentService implements PaymentService {

    private MoMoEnvironment environment;
    @Autowired
    private PaymentDetailRepository paymentDetailRepository;
    @Autowired
    private PaymentOrderRepository paymentOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

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

    @Override
    @Transactional
    public String processCallBack(JsonNode request) {
        try {
            System.out.println("Request JSON: " + request.toPrettyString());
            MomoCallBackRequest momoCallBackRequest = MapMomoCallBack.map(request);

            PaymentOrder newpaymentOrder = mapToOrderPayment(momoCallBackRequest.getOrderId(),
                    momoCallBackRequest.getRequestId(),
                    momoCallBackRequest.getAmount());

            PaymentDetail newpaymentDetail = new PaymentDetail();

            newpaymentDetail.setPayment_id(newpaymentOrder);
            newpaymentDetail.setDetailData(request);

            paymentOrderRepository.save(newpaymentOrder);
            paymentDetailRepository.save(newpaymentDetail);

            return "process call back successfully!";
        } catch (Exception e) {
            System.err.println("Lỗi xử lý thanh toán: " + e.getMessage());
            e.printStackTrace();
            return "Transaction failed!";
        }

    }

    public void display() {
        System.out.println("Call MoMo Servie");
    }

    private PaymentOrder mapToOrderPayment(String orderId, String customerId, Long amount) {
        Long orderIdLong = Long.parseLong(orderId);
        Long customerIdLong = Long.parseLong(customerId);

        Customer customer = customerRepository.findById(customerIdLong)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Customer với ID: " + customerId));

        PaymentMethod paymentMethod = paymentMethodRepository.findMethodByName("MOMO");
        if (paymentMethod == null) {
            throw new EntityNotFoundException("Không tìm thấy phương thức thanh toán MOMO");
        }

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId(orderIdLong);
        paymentOrder.setCustomer(customer);
        paymentOrder.setAmount(amount);
        paymentOrder.setPaymentMethod(paymentMethod);
        paymentOrder.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentOrder.setCreatedAt(LocalDateTime.now());
        return paymentOrder;
    }

}
