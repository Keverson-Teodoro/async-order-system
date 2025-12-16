    package io.github.Keverson_Teodoro.payment_service.conexions;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import org.springframework.amqp.core.*;
    import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
    import org.springframework.amqp.rabbit.connection.ConnectionFactory;
    import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class RabbitMQConexion {

        @Autowired
        private AmqpAdmin amqpAdmin;

        @Bean
        public Jackson2JsonMessageConverter messageConverter() {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return new Jackson2JsonMessageConverter(objectMapper);
        }

        @Bean
        public SimpleRabbitListenerContainerFactory containerFactory (ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

            factory.setConnectionFactory(connectionFactory);
            factory.setMessageConverter(messageConverter);
            return factory;
        }

        @Bean
        public Queue paymetSuccssed(){
            return new Queue("payment.succeeded", true, false, false);
        }

        @Bean
        public DirectExchange paymentDirect(){
            return new DirectExchange("paymentSucceeded.direct", true, false, null);
        }

        @Bean
        public Binding paymentSuccessBinding(Queue queue, DirectExchange paymentExchange){
            return BindingBuilder.bind(queue).to(paymentExchange).with("paymentSucceeded.direct");
        }
    }
