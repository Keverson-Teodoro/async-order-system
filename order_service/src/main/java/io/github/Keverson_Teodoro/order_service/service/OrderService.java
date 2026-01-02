package io.github.Keverson_Teodoro.order_service.service;

import io.github.Keverson_Teodoro.order_service.DTO.AddressDTO;
import io.github.Keverson_Teodoro.order_service.DTO.NewOrderDTO;
import io.github.Keverson_Teodoro.order_service.DTO.OrderEventDTO;
import io.github.Keverson_Teodoro.order_service.DTO.ProductResponseDTO;
import io.github.Keverson_Teodoro.order_service.model.entity.Address;
import io.github.Keverson_Teodoro.order_service.model.entity.Order;
import io.github.Keverson_Teodoro.order_service.model.enums.OrderStatus;
import io.github.Keverson_Teodoro.order_service.producers.OrderEventProducer;
import io.github.Keverson_Teodoro.order_service.repository.AddressRepository;
import io.github.Keverson_Teodoro.order_service.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderEventProducer orderEventProducer;

    public void newOrder(NewOrderDTO newOrderDTO){
        boolean clientExist = userService.userExistResponse(newOrderDTO.emailCustumer());

        Address address = addressRepository.findById(newOrderDTO.idAddress()).orElseThrow( () -> new RuntimeException("Endereço não encontrado"));
        AddressDTO addressDTO = new AddressDTO(address.getStreet(), address.getCity(), address.getNumber(), address.getCep());
        if (!clientExist) throw new RuntimeException("Usuário não encontrado");

        Order order = new Order();
        BeanUtils.copyProperties(order, newOrderDTO);

        byte[] hashByte = Base64.getEncoder().encode(newOrderDTO.paymentMethod().getBytes());
        String token = Base64.getEncoder().encodeToString(hashByte);

        List<ProductResponseDTO> orderItems = productService.validateOrderItems(newOrderDTO.items());

        if (orderItems == null) {
            throw new RuntimeException("produtos não encontrados.");
        }

        Double total = 0.0;
        for (ProductResponseDTO product : orderItems) {
            total += product.getPrice();
        }

        order.setPaymentMethod(token);
        order.setAddress(address);
        order.setCreatedAt(LocalDateTime.now());
        order.setItems(orderItems);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setEmailCustomer(newOrderDTO.emailCustumer());
        order.setTotal(total);

        OrderEventDTO orderEventDTO = new OrderEventDTO();
        BeanUtils.copyProperties(order, orderEventDTO);
        orderEventDTO.setAddress(addressDTO);

        orderRepository.save(order);
        orderEventProducer.publishMessage(orderEventDTO);

    }
}
