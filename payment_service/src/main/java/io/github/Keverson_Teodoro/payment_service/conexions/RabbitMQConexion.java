package io.github.Keverson_Teodoro.payment_service.conexions;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConexion {

    @Autowired
    private AmqpAdmin amqpAdmin;


    @Bean
    public Queue paymetSuccssed(){
        return new Queue("payment.success", true, false, false);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public DirectExchange paymentDirect(){
        return new DirectExchange("payment.direct", true, false, null);
    }

    @Bean
    public Binding paymentSuccessBinding(Queue queue, DirectExchange paymentExchange){
        return BindingBuilder.bind(queue).to(paymentExchange).with("payment.direct");
    }


    @PostConstruct
    public void addQueues(){

        amqpAdmin.declareQueue(paymetSuccssed());
        amqpAdmin.declareExchange(paymentDirect());
        amqpAdmin.declareBinding(paymentSuccessBinding(paymetSuccssed(), paymentDirect()));

    }
}
