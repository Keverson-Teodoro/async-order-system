package io.github.Keverson_Teodoro.notification_service.DTO;

import io.github.Keverson_Teodoro.notification_service.model.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

public record PaymentSuccesEventDTO(String idPayment,
                                    double amount,
                                    String method,
                                    PaymentStatus paymentStatus,
                                    LocalDateTime processedAt,
                                    List<ProductResponseDTO> items,
                                    String customerEmail) {
}
