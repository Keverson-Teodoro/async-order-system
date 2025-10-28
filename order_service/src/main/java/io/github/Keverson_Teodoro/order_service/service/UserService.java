package io.github.Keverson_Teodoro.order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:9094/user")
            .defaultHeader("Content-type", "application/json")
            .build();

    public boolean userExistResponse(String id){
        String body = String.format("{\"id\": \"%s\"}", id);

        return webClient.post()
                .uri("/userExist")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

    }
}
