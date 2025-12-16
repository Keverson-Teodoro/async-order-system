package io.github.Keverson_Teodoro.order_service.service;

import io.github.Keverson_Teodoro.order_service.DTO.ProductResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:9093/product")
            .defaultHeader("Content-type", "application/json")
            .build();

    public List<ProductResponseDTO> validateOrderItems (List<String> names){
        String body = String.format("{\"names\": \"%s\"}", names);
        return webClient.post()
                .uri("/existents")
                .bodyValue(names)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductResponseDTO>>() {})
                .block();
    }
}
