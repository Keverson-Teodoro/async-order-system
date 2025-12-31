package io.github.Keverson_Teodoro.product_service.repository;


import io.github.Keverson_Teodoro.product_service.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByName(String name);

}
