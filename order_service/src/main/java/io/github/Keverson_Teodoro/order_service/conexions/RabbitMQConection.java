package io.github.Keverson_Teodoro.order_service.conexions;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public class RabbitMQConection {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }


    @Bean
    public Queue orderCreated(){
        return new Queue("order.created", true, false, false);
    }


    @Bean
    public DirectExchange exchangeOrderCreated(){
        return new DirectExchange("orders.direct", true, false);
    }

    @Bean
    public Binding bidingOrderCreated (Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("orders.direct");
    }


    @PostConstruct
    public void addQueueOrderCreated(){

        amqpAdmin.declareQueue(orderCreated());
        amqpAdmin.declareExchange(exchangeOrderCreated());
        amqpAdmin.declareBinding(bidingOrderCreated(orderCreated(), exchangeOrderCreated()));
    }




}
