package io.github.Keverson_Teodoro.order_service.model.entity;

import io.github.Keverson_Teodoro.order_service.DTO.ProductResponseDTO;
import io.github.Keverson_Teodoro.order_service.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "id_costumer")
    private String customerId;

    @Column(name = "total")
    private double total;

    @JoinColumn(name = "id_address")
    @ManyToOne
    private Address address;

    @Column(name = "payment_token")
    private String paymentMethod;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<ProductResponseDTO> items;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
