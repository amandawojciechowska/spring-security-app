package com.example.springsecurityapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProducerEntity producer;

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;
}
