package io.github.Keverson_Teodoro.order_service.consumer;

import io.github.Keverson_Teodoro.order_service.DTO.ProductResponseDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConsumer {

    @RabbitListener(queues = "product.receiver")
    public List<ProductResponseDTO> productReceiverConsumer (List<ProductResponseDTO> productResponseDTO){
        return productResponseDTO;
    }
}
