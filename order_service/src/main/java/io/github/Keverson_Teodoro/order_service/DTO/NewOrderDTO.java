package io.github.Keverson_Teodoro.order_service.DTO;

import io.github.Keverson_Teodoro.order_service.model.entity.Address;
import io.github.Keverson_Teodoro.order_service.model.entity.Product;

import java.util.List;

public record NewOrderDTO(String idCustomer, List<Product> items, Address address) {
}
