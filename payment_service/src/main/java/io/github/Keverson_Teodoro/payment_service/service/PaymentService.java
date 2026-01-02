package io.github.Keverson_Teodoro.payment_service.service;

import io.github.Keverson_Teodoro.payment_service.DTO.OrderEventDTO;
import io.github.Keverson_Teodoro.payment_service.DTO.PaymentResponseEventDTO;
import io.github.Keverson_Teodoro.payment_service.model.entity.Payment;
import io.github.Keverson_Teodoro.payment_service.model.enums.PaymentStatus;
import io.github.Keverson_Teodoro.payment_service.repository.PaymentRepository;
import org.springframework.beans.BeanUtils;
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

    public PaymentResponseEventDTO verifyPayment(OrderEventDTO order){
        boolean isAproved = simulatePaymentAproval();

        Payment payment = new Payment();
        PaymentResponseEventDTO paymentResponseEventDTO = new PaymentResponseEventDTO(
                payment.getIdPayment(),
                order.getTotal(),
                order.getPaymentMethod(),
                PaymentStatus.FAILED,
                LocalDateTime.now(),
                order.getItems(),
                order.getCustomerEmail());

        if(isAproved){
            byte[] decodedTokenByetes = Base64.getDecoder().decode(order.getPaymentMethod());
            String method = new String(decodedTokenByetes, StandardCharsets.UTF_8);
            paymentResponseEventDTO.setPaymentStatus(PaymentStatus.SUCCEEDED);
            paymentResponseEventDTO.setMethod(method);
        }
        BeanUtils.copyProperties(paymentResponseEventDTO, payment);
        paymentRepository.save(payment);
        return paymentResponseEventDTO;
    }

    private boolean simulatePaymentAproval(){
        Random random = new Random();
        return random.nextBoolean();
    }
}
