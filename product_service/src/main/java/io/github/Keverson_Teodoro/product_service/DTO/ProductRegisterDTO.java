package io.github.Keverson_Teodoro.product_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterDTO(@NotBlank(message = "Nome do produto é obrigatório") String name,
                                 @NotNull(message = "Preço do produto é obrigatório") Double price,
                                 @NotBlank(message = "quantidade do produto é obrigatório") Integer quantity) {
}
