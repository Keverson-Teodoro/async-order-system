package io.github.Keverson_Teodoro.product_service.DTO;

import io.github.Keverson_Teodoro.product_service.model.enums.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentResponseDTO (
        String idPayment,
        double amount,
        String method,
        PaymentStatus paymentStatus,
        LocalDateTime processedAt
) {
}
