package com.example.springsecurityapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCER")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}
