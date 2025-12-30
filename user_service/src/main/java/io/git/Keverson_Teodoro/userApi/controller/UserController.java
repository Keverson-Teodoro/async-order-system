package io.git.Keverson_Teodoro.userApi.controller;

import io.git.Keverson_Teodoro.userApi.DTO.UserRegisterDTO;
import io.git.Keverson_Teodoro.userApi.DTO.VerifyUserExistDTO;
import io.git.Keverson_Teodoro.userApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registrateUser( @Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return userService.registrateUser(userRegisterDTO);
    }

    @PostMapping("/userExist")
    public boolean userExistByEmail(@RequestBody VerifyUserExistDTO id){
        return userService.userExistById(id);
    }
}
