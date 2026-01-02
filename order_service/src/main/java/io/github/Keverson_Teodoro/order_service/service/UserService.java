package io.github.Keverson_Teodoro.order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:9094/user")
            .defaultHeader("Content-type", "application/json")
            .build();

    public boolean userExistResponse(String email){
        String body = String.format("{\"email\": \"%s\"}", email);

        return Boolean.TRUE.equals(webClient.post()
                .uri("/userExist")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());

    }
}
