package com.example.springsecurityapp.service.impl;

import com.example.springsecurityapp.entity.CartEntity;
import com.example.springsecurityapp.mapper.ProductMapper;
import com.example.springsecurityapp.model.ProductTo;
import com.example.springsecurityapp.repository.CartRepository;
import com.example.springsecurityapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public CartEntity getOrCreateCartForUser(String username) {
        Optional<CartEntity> cart = cartRepository.findByUserName(username);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            CartEntity newCart = new CartEntity();
            newCart.setUserName(username);
            return cartRepository.save(newCart);
        }
    }

    @Override
    public List<ProductTo> getProductsFromCartForUser(String username) {
        return ProductMapper.mapProductEntities2Tos(cartRepository.findCartItemsByUsername(username));
    }

}
