    package io.github.Keverson_Teodoro.payment_service.conexions;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import jakarta.annotation.PostConstruct;
    import org.springframework.amqp.core.*;
    import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
    import org.springframework.amqp.rabbit.connection.ConnectionFactory;
    import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Primary;
    import org.springframework.stereotype.Component;

    @Component
    public class RabbitMQConexion {

        @Autowired
        private AmqpAdmin amqpAdmin;

        @Bean("messageConverter")
        public Jackson2JsonMessageConverter messageConverter() {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return new Jackson2JsonMessageConverter(objectMapper);
        }

        @Bean("containerFactory")
        public SimpleRabbitListenerContainerFactory containerFactory (ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

            factory.setConnectionFactory(connectionFactory);
            factory.setMessageConverter(messageConverter);
            return factory;
        }

        @Bean("paymentSuccessQueue")
        public Queue paymetSuccessQueue(){
            return new Queue("payment.success", true, false, false);
        }

        @Bean("paymentSuccessDirect")
        @Primary
        public DirectExchange paymentSuccessDirect(){
            return new DirectExchange("paymentSuccess.direct", true, false, null);
        }

        @Bean("paymentSuccessBinding")
        public Binding paymentSuccessBinding(Queue paymentSuccessQueue, DirectExchange paymentSuccessExchange){
            return BindingBuilder.bind(paymentSuccessQueue).to(paymentSuccessExchange).with("payment.success");
        }

        @Bean("paymentFailedQueue")
        public Queue paymentFailedQueue() {
            return new Queue("payment.failed", true, false, false);
        }

        @Bean("paymentFailedExchange")
        public DirectExchange paymentFailedExchange() {
            return new DirectExchange("paymentFailed.direct", true, false, null);
        }

        @Bean("paymentFaileBinding")
        public Binding paymentFailedBinding (Queue paymentFailedQueue, DirectExchange paymentFailedExchange) {
            return BindingBuilder.bind(paymentFailedQueue).to(paymentFailedExchange).with("payment.failed");
        }

        @PostConstruct
        public void add () {
            amqpAdmin.declareQueue(paymetSuccessQueue());
            amqpAdmin.declareExchange(paymentSuccessDirect());
            amqpAdmin.declareBinding(paymentSuccessBinding(paymetSuccessQueue(), paymentSuccessDirect()));

            amqpAdmin.declareQueue(paymentFailedQueue());
            amqpAdmin.declareExchange(paymentFailedExchange());
            amqpAdmin.declareBinding(paymentFailedBinding(paymentFailedQueue(), paymentFailedExchange()));
        }
    }
