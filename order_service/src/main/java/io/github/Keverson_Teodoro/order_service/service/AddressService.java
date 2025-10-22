package io.github.Keverson_Teodoro.order_service.service;

import io.github.Keverson_Teodoro.order_service.DTO.AddressDTO;
import io.github.Keverson_Teodoro.order_service.model.entity.Address;
import io.github.Keverson_Teodoro.order_service.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public void saveAddress(AddressDTO addressDTO){

        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        addressRepository.save(address);
    }


}
