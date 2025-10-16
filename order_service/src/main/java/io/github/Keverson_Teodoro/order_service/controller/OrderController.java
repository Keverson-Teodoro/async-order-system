package io.github.Keverson_Teodoro.order_service.controller;

import io.github.Keverson_Teodoro.order_service.DTO.NewOrderDTO;
import io.github.Keverson_Teodoro.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping
    public void newOrder(@RequestBody NewOrderDTO newOrderDTO){
        orderService.newOrder(newOrderDTO);
    }


}
