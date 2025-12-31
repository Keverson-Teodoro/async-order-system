package io.github.Keverson_Teodoro.payment_service.consumers;

import io.github.Keverson_Teodoro.payment_service.DTO.OrderEventDTO;
import io.github.Keverson_Teodoro.payment_service.DTO.PaymentResponseEventDTO;
import io.github.Keverson_Teodoro.payment_service.model.enums.PaymentStatus;
import io.github.Keverson_Teodoro.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final PaymentService paymentService;

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "orders.created", containerFactory = "containerFactory")
    public void paymentAprovment(@Payload OrderEventDTO orderEvent){

        PaymentResponseEventDTO payment = paymentService.verifyPayment(orderEvent);

        if(payment.getPaymentStatus() == PaymentStatus.FAILED) {
            rabbitTemplate.convertAndSend("payment.failed", payment);
        }
        if (payment.getPaymentStatus() == PaymentStatus.SUCCEEDED) {
            rabbitTemplate.convertAndSend("payment.success", payment);
        }
    }
}
