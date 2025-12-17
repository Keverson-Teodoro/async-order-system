package io.github.Keverson_Teodoro.payment_service.service;

import io.github.Keverson_Teodoro.payment_service.DTO.OrderEventDTO;
import io.github.Keverson_Teodoro.payment_service.model.entity.Payment;
import io.github.Keverson_Teodoro.payment_service.model.enums.PaymentStatus;
import io.github.Keverson_Teodoro.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment verifyPayment(OrderEventDTO order){
        boolean isAproved = simulatePaymentAproval();

        Payment payment = new Payment(order.getTotal(), order.getPaymentMethod(), PaymentStatus.FAILED, LocalDateTime.now());

        if(isAproved){
            byte[] decodedTokenByetes = Base64.getDecoder().decode(order.getPaymentMethod());
            String method = new String(decodedTokenByetes, StandardCharsets.UTF_8);
            payment.setMethod(method);
            payment.setPaymentStatus(PaymentStatus.SUCCEEDED);
        }
        return payment;
    }

    private boolean simulatePaymentAproval(){
        Random random = new Random();
        return random.nextBoolean();
    }
}
