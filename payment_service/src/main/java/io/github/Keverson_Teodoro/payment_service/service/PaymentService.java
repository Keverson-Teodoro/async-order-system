package io.github.Keverson_Teodoro.payment_service.service;

import io.github.Keverson_Teodoro.payment_service.DTO.OrderEventDTO;
import io.github.Keverson_Teodoro.payment_service.model.entity.Payment;
import io.github.Keverson_Teodoro.payment_service.model.enums.PaymentStatus;
import io.github.Keverson_Teodoro.payment_service.repository.PaymentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment verifyPayment(OrderEventDTO order){
        boolean isAproved = simulatePaymentAproval();

        if(isAproved){
            byte[] decodedTokenByetes = Base64.getDecoder().decode(order.getPaymentMethod());
            String method = new String(decodedTokenByetes, StandardCharsets.UTF_8);

            Payment payment = new Payment(order.getTotal(), method, PaymentStatus.SUCCEEDED, LocalDateTime.now());
            return payment;
        }
        return null;
    }

    private boolean simulatePaymentAproval(){
        Random random = new Random();
        return random.nextBoolean();
    }
}
