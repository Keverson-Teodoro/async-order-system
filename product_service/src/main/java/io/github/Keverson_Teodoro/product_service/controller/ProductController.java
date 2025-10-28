package io.github.Keverson_Teodoro.product_service.controller;

import io.github.Keverson_Teodoro.product_service.DTO.ProductIdRequestDTO;
import io.github.Keverson_Teodoro.product_service.DTO.ProductNamesRequestDTO;
import io.github.Keverson_Teodoro.product_service.DTO.ProductRegisterDTO;
import io.github.Keverson_Teodoro.product_service.DTO.ProductResponseDTO;
import io.github.Keverson_Teodoro.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public void saveNewProduct(@RequestBody ProductRegisterDTO productRegisterDTO){
        productService.saveProduct(productRegisterDTO);
    }

    @PostMapping("/verifyProductExist")
    public boolean verifyProduct(@RequestBody @Valid ProductIdRequestDTO productIdDTO){
        return productService.verifyProduct(productIdDTO);
    }

    @PostMapping("/existents")
    public List<ProductResponseDTO> validadeProductNames(@RequestBody List<String> productNames){
        return productService.existentsProducts(productNames);
    }




}
