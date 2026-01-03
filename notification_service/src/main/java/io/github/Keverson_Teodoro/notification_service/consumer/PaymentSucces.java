package io.github.Keverson_Teodoro.notification_service.consumer;

import io.github.Keverson_Teodoro.notification_service.DTO.PaymentSuccesEventDTO;
import io.github.Keverson_Teodoro.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSucces {

    private final NotificationService notificationService;

    @RabbitListener(queues = "payment.success")
    public void sendEmail (@Payload PaymentSuccesEventDTO paymentSuccesEventDTO) throws Exception {

        notificationService.sendEmail(paymentSuccesEventDTO);
    }
}
