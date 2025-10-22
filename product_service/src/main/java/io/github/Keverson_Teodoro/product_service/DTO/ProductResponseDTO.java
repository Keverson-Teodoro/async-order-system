package io.github.Keverson_Teodoro.product_service.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {

    private String name;
    private Double price;
    private Integer quantity;
}
