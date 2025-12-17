package io.github.Keverson_Teodoro.product_service.consumers;

import io.github.Keverson_Teodoro.product_service.DTO.PaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSuccessConsumer {

    @RabbitListener(queues = "payment.success")
    public void paymentSuccessListenner (@Payload PaymentResponseDTO object) {

        System.out.println(object);

    }
}
