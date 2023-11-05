package com.example.springsecurityapp.service;

import com.example.springsecurityapp.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> getAllProducts();

}
