package com.example.springsecurityapp.repository;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();

    List<ProductEntity> findByProducer(ProducerEntity producer);

    List<ProductEntity> findByNameContaining(String name);

}
