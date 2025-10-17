package io.github.Keverson_Teodoro.payment_service.repository;

import io.github.Keverson_Teodoro.payment_service.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
