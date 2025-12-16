package io.github.Keverson_Teodoro.payment_service.consumers;

import io.github.Keverson_Teodoro.payment_service.DTO.OrderEventDTO;
import io.github.Keverson_Teodoro.payment_service.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @Autowired
    private PaymentService paymentService;


    @RabbitListener(queues = "orders.created", containerFactory = "containerFactory")
    public void paymentAprovment(@Payload OrderEventDTO orderEvent){

    }
}
