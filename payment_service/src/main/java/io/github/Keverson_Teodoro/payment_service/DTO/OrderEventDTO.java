package io.github.Keverson_Teodoro.payment_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDTO implements Serializable {

    private String id;

    private String customerEmail;

    private double total;

    private AddressDTO address;

    private String paymentMethod;

    private List<ProductReponseDTO> items;

    private String orderStatus;

    private LocalDateTime createdAt;
}
