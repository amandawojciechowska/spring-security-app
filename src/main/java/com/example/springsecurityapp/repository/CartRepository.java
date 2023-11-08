package com.example.springsecurityapp.repository;

import com.example.springsecurityapp.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByUserName(String username);

}
