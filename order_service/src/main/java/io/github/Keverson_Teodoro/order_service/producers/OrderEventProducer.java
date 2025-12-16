package io.github.Keverson_Teodoro.order_service.producers;

import io.github.Keverson_Teodoro.order_service.DTO.OrderEventDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishMessage(OrderEventDTO order){
        rabbitTemplate.convertAndSend("orders.created", order);
    }
}
