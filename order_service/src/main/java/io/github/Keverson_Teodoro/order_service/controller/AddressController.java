package io.github.Keverson_Teodoro.order_service.controller;

import io.github.Keverson_Teodoro.order_service.DTO.AddressDTO;
import io.github.Keverson_Teodoro.order_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping
    public void saveAddress(@RequestBody AddressDTO addressDTO){

        addressService.saveAddress(addressDTO);

    }
}
