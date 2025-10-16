package io.github.Keverson_Teodoro.order_service.service;

import io.github.Keverson_Teodoro.order_service.DTO.NewOrderDTO;
import io.github.Keverson_Teodoro.order_service.model.entity.Order;
import io.github.Keverson_Teodoro.order_service.model.entity.Product;
import io.github.Keverson_Teodoro.order_service.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;


    public void newOrder(NewOrderDTO newOrderDTO){

        boolean clientExist = userService.userExistResponse(newOrderDTO.idCustomer());
        if (!clientExist) throw new RuntimeException("Usuario não encontrado");

        Order order = new Order();
        BeanUtils.copyProperties(newOrderDTO, order);

        double totalOrder = 0.0;
        for(Product product : newOrderDTO.items()){
            totalOrder += product.getPrice();
        }
        order.setTotal(totalOrder);
        orderRepository.save(order);
    }
}
