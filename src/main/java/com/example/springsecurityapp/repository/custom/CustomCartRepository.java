package com.example.springsecurityapp.repository.custom;

import com.example.springsecurityapp.entity.ProductEntity;

import java.util.List;

public interface CustomCartRepository {

    List<ProductEntity> findCartItemsByUsername(String username);

}
