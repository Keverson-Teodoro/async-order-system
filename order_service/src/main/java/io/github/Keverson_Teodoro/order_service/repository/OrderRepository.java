package io.github.Keverson_Teodoro.order_service.repository;

import io.github.Keverson_Teodoro.order_service.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
