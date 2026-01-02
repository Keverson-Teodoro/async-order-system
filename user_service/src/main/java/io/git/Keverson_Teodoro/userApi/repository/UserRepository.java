package io.git.Keverson_Teodoro.userApi.repository;

import io.git.Keverson_Teodoro.userApi.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail (String email);

    Optional<UserEntity> findByTelefone(String telefone);
}
