package com.example.springsecurityapp.repository;

import com.example.springsecurityapp.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
