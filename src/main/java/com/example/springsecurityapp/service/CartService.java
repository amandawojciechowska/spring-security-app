package com.example.springsecurityapp.service;

import com.example.springsecurityapp.entity.CartEntity;
import com.example.springsecurityapp.model.ProductTo;

import java.util.List;

public interface CartService {

    CartEntity getOrCreateCartForUser(String username);

    List<ProductTo> getProductsFromCartForUser(String username);

}
