package com.example.springsecurityapp.repository;

import com.example.springsecurityapp.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

}
