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

        UserEntity user = new UserEntity();
        user.setNome(userRegisterDTO.nome());
        user.setEmail(userRegisterDTO.email());
        user.setTelefone(userRegisterDTO.telefone());
        System.out.println(userRegisterDTO);
        userRepository.save(user);

        return ResponseEntity.ok("Usuario cadastrado com sucesso");
    }

    public boolean userExistById(VerifyUserExistDTO id){

        Optional<UserEntity> user = userRepository.findById(id.id());
        return user.isPresent();

    }
}
