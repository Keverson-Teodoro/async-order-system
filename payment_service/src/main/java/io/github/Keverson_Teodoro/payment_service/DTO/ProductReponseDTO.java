package io.github.Keverson_Teodoro.payment_service.DTO;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductReponseDTO implements Serializable {

    private String name;
    private Double price;
    private Integer quantity;
}
