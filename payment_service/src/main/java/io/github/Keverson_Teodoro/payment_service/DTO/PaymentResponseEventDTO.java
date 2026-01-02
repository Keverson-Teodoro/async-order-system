package io.github.Keverson_Teodoro.payment_service.DTO;

import io.github.Keverson_Teodoro.payment_service.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponseEventDTO {

        private String idPayment;
        private double amount;
        private String method;
        private PaymentStatus paymentStatus;
        private LocalDateTime processedAt;
        private List<ProductReponseDTO> items;
        private String customerEmail;
}
