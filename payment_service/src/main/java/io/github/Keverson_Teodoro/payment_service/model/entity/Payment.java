package io.github.Keverson_Teodoro.payment_service.model.entity;

import io.github.Keverson_Teodoro.payment_service.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_table")
@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPayment;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_method")
    private String method;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Column(name = "customer_email")
    private String CustomerEmail;
}
