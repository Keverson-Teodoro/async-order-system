package io.github.Keverson_Teodoro.product_service.consumers;

import io.github.Keverson_Teodoro.product_service.DTO.PaymentResponseDTO;
import io.github.Keverson_Teodoro.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final ProductService productService;

    @RabbitListener(queues = "payment.failed")
    public void paymentFailedListenner (@Payload PaymentResponseDTO paymentFailed) {

        System.out.println(paymentFailed);
        productService.setStockQuantity(paymentFailed.items());
    }
}
