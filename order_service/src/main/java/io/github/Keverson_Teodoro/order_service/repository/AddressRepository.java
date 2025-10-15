package io.github.Keverson_Teodoro.order_service.repository;

import io.github.Keverson_Teodoro.order_service.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
