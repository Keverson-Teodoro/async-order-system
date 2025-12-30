package io.git.Keverson_Teodoro.userApi.repository;

import io.git.Keverson_Teodoro.userApi.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, String> {
}
