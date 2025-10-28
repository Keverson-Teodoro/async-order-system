package io.github.Keverson_Teodoro.order_service.conexions;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public Queue orderCreated(){
        return new Queue("orders.created", true, false, false);
    }

    @Bean
    public DirectExchange exchangeOrderCreated(){
        return new DirectExchange("orders.direct", true, false);
    }

//    @Bean
//    public Binding bidingOrderCreated (Queue queue, DirectExchange directExchange){
//        return BindingBuilder.bind(queue).to(directExchange).with("orders.direct");
//    }
//
    @Bean
    public Binding bindingOrdersDirect (Queue queue, DirectExchange directExchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), "orders.created", null);
    }

    @PostConstruct
    public void addQueueOrderCreated(){
        amqpAdmin.declareQueue(orderCreated());
        amqpAdmin.declareExchange(exchangeOrderCreated());
        amqpAdmin.declareBinding(bindingOrdersDirect(orderCreated(), exchangeOrderCreated()));

    }
}
