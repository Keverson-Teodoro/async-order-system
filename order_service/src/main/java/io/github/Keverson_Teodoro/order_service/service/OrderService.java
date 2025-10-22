package io.github.Keverson_Teodoro.order_service.service;

import io.github.Keverson_Teodoro.order_service.DTO.NewOrderDTO;
import io.github.Keverson_Teodoro.order_service.model.entity.Address;
import io.github.Keverson_Teodoro.order_service.model.entity.Order;
import io.github.Keverson_Teodoro.order_service.repository.AddressRepository;
import io.github.Keverson_Teodoro.order_service.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AddressRepository addressRepository;


    public void newOrder(NewOrderDTO newOrderDTO){

        boolean clientExist = userService.userExistResponse(newOrderDTO.idCustomer());

        Address address = addressRepository.findById(newOrderDTO.idAddress()).orElseThrow( () -> new RuntimeException("Não endereço não encontrado"));
        if (!clientExist) throw new RuntimeException("Usuario não encontrado");


        Order order = new Order();
        BeanUtils.copyProperties(newOrderDTO, order);

        byte[] hashByte = Base64.getEncoder().encode(newOrderDTO.paymentMethod().getBytes());
        String token = Base64.getEncoder().encodeToString(hashByte);

        order.setPaymentToken(token);
        order.setAddress(address);


//        double totalOrder = 0.0;
//        for(Product product : newOrderDTO.items()){
//            totalOrder += product.getPrice();
//        }

//        order.setTotal(totalOrder);
        orderRepository.save(order);
        rabbitTemplate.convertAndSend("orders.direct", order);
    }



}
