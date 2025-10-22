package io.github.Keverson_Teodoro.order_service.DTO;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(@NotBlank String street, @NotBlank String city, String number, @NotBlank String cep) {
}
