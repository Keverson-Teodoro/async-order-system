package io.github.Keverson_Teodoro.order_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseDTO implements Serializable {

    private String name;
    private Double price;
    private Integer quantity;
}
