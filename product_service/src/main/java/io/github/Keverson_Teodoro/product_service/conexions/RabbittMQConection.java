package io.github.Keverson_Teodoro.product_service.conexions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbittMQConection {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue productSend(){
        return new Queue("product.send", true, false, false);
    }

    @Bean
    public Queue productReceiver(){
        return new Queue("product.receiver", true, false, false);
    }

    @Bean
    public DirectExchange productSendExchange(){
        return new DirectExchange("productSend.direct", true, false);
    }

    @Bean
    public DirectExchange productReceiverExchange(){
        return new DirectExchange("productReceiver.direct", true, false);
    }

    @Bean
    public Binding productReceiverBinding(){
        return BindingBuilder.bind(productReceiver())
                .to(productReceiverExchange())
                .with("productReceiver.direct");
    }

    public Binding bindingProductQueue (){
        return BindingBuilder.bind(productSend())
                .to(productSendExchange())
                .with("productSend.direct");
    }

    @PostConstruct
    public void add(){
        amqpAdmin.declareQueue(productSend());
        amqpAdmin.declareExchange(productSendExchange());
        amqpAdmin.declareBinding(bindingProductQueue());

        amqpAdmin.declareQueue(productReceiver());
        amqpAdmin.declareExchange(productReceiverExchange());
        amqpAdmin.declareBinding(productReceiverBinding());
    }
}
