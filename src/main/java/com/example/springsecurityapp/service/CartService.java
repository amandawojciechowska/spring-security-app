package com.example.springsecurityapp.service;

import com.example.springsecurityapp.entity.CartEntity;

public interface CartService {

    CartEntity getOrCreateCartForUser(String username);

}
