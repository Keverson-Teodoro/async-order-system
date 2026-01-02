package io.github.Keverson_Teodoro.notification_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:9094/user")
            .defaultHeader("Content-type", "application/json")
            .build();

    public void getUserEmail(String idUser){

        String url = "http://localhost:909"


    }

}

