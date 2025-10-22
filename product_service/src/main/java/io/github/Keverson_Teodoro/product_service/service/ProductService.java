package io.github.Keverson_Teodoro.product_service.service;


import io.github.Keverson_Teodoro.product_service.DTO.ProductIdRequestDTO;
import io.github.Keverson_Teodoro.product_service.DTO.ProductRegisterDTO;
import io.github.Keverson_Teodoro.product_service.DTO.ProductResponseDTO;
import io.github.Keverson_Teodoro.product_service.model.entity.Product;
import io.github.Keverson_Teodoro.product_service.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public void saveProduct(ProductRegisterDTO productRegisterDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productRegisterDTO, product);

        productRepository.save(product);
    }


    public boolean verifyProduct (ProductIdRequestDTO productIdRequestDTO){
        Optional<Product> product = productRepository.findById(productIdRequestDTO.id());
        return product.isPresent();
    }

    public List<ProductResponseDTO> existentsProducts(List<String> produtNames){

        List<ProductResponseDTO> products = new ArrayList<>();
        List<String> existingNames = new ArrayList<>();

        productRepository.findAll().forEach(p -> {
            if(!existingNames.contains(p.getName())){
                existingNames.add(p.getName());
            }

        });

        produtNames.forEach(x -> {
            if(!existingNames.contains(x)){
                throw new RuntimeException("Produto não encontrado " + x);
            }
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            Product product = productRepository.findByName(x);
            BeanUtils.copyProperties(product, productResponseDTO);
            products.add(productResponseDTO);

        });

        return products;
    }

}
