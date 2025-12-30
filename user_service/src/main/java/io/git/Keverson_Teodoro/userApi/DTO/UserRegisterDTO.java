package io.git.Keverson_Teodoro.userApi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(@NotNull String nome, @Email String email, String telefone) {

}
