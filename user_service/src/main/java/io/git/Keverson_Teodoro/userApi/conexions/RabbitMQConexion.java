package io.git.Keverson_Teodoro.userApi.conexions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.git.Keverson_Teodoro.userApi.model.constraints.RabbitMQConstraints;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public class RabbitMQConexion {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue userQueue(){
        return new Queue(RabbitMQConstraints.userQueue, true, false, false);
    }

    @Bean
    public DirectExchange directExchange (){
        return new DirectExchange(RabbitMQConstraints.exchangeName, true, false);
    }

    @Bean
    public Binding bindingUserQueue (Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(userQueue()).to(directExchange).with(RabbitMQConstraints.routinKey);
    }

    @PostConstruct
    public void add(){
        amqpAdmin.declareQueue(userQueue());
        amqpAdmin.declareExchange(directExchange());
        amqpAdmin.declareBinding(bindingUserQueue(userQueue(), directExchange()));
    }
}
