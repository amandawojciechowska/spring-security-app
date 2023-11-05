package com.example.springsecurityapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String client;

    @Column
    private String product;

    @Column
    private Long quantity;

}
