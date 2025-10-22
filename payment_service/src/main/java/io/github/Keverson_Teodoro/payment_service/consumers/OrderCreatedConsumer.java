package io.github.Keverson_Teodoro.payment_service.consumers;

import io.github.Keverson_Teodoro.payment_service.DTO.OrderEventDTO;
import io.github.Keverson_Teodoro.payment_service.model.entity.Payment;
import io.github.Keverson_Teodoro.payment_service.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @Autowired
    private PaymentService paymentService;


    @RabbitListener(queues = "order.created")
    public void paymentAprovment(OrderEventDTO orderEvent){

        Payment payment = paymentService.verifyPayment(orderEvent);
        if (payment == null){
            System.out.println("consumer iniciado");
        }
        System.out.println(orderEvent);

    }


}
