package io.github.Keverson_Teodoro.payment_service.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record OrderEventDTO (String orderId, String customerId, List<Object> items, double total, Object address, String paymentToken, LocalDateTime createdAt){



}
