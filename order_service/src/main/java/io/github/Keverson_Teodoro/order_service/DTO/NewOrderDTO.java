package io.github.Keverson_Teodoro.order_service.DTO;



import java.util.List;

public record NewOrderDTO(String idCustomer, List<Object> items, String idAddress, String paymentMethod) {

}
