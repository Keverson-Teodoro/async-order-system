package io.git.Keverson_Teodoro.userApi.service;

import io.git.Keverson_Teodoro.userApi.DTO.UserRegisterDTO;
import io.git.Keverson_Teodoro.userApi.DTO.VerifyUserExistDTO;
import io.git.Keverson_Teodoro.userApi.model.entity.UserEntity;
import io.git.Keverson_Teodoro.userApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> registrateUser(UserRegisterDTO userRegisterDTO){

        if (userRepository.findByEmail(userRegisterDTO.email()).isPresent() || userRepository.findByTelefone(userRegisterDTO.telefone()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já cadastrado");
        }
        UserEntity user = new UserEntity(userRegisterDTO.nome(), userRegisterDTO.email(), userRegisterDTO.telefone());
        userRepository.save(user);

        return ResponseEntity.ok("Usuario cadastrado com sucesso");
    }

    public boolean userExistById(VerifyUserExistDTO verifyUserExistDTO){

        Optional<UserEntity> user = userRepository.findByEmail(verifyUserExistDTO.email());
        return user.isPresent();

    }
}
