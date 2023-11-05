package com.example.springsecurityapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "PRODUCER")
public class ProducerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "producer")
    private Collection<ProductEntity> products;

}
