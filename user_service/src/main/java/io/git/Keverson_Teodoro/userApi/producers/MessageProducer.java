package io.git.Keverson_Teodoro.userApi.producers;

import io.git.Keverson_Teodoro.userApi.DTO.EmailDTO;
import io.git.Keverson_Teodoro.userApi.model.constraints.RabbitMQConstraints;
import io.git.Keverson_Teodoro.userApi.model.entity.UserEntity;
import io.git.Keverson_Teodoro.userApi.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserService userService;


    public void welcomeMessage(UserEntity userEntity){
        EmailDTO emailDTO = new EmailDTO(userEntity.getId(), userEntity.getEmail(), "Cadastro Realizado", "bem vindo, " + userEntity.getNome());
        rabbitTemplate.convertAndSend(RabbitMQConstraints.userQueue, emailDTO);
    }


}
