package com.example.springsecurityapp.controller;

import com.example.springsecurityapp.entity.CartEntity;
import com.example.springsecurityapp.entity.CartItemEntity;
import com.example.springsecurityapp.entity.ProductEntity;
import com.example.springsecurityapp.model.CartItemRequest;
import com.example.springsecurityapp.model.ProductTo;
import com.example.springsecurityapp.repository.CartItemRepository;
import com.example.springsecurityapp.service.CartService;
import com.example.springsecurityapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    @GetMapping("/{username}")
    public List<ProductTo> getCartWithProductsByUsername(@PathVariable("username") String username) {
        return null;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addItemToCart(@RequestBody CartItemRequest cartItemRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")) && authentication.isAuthenticated()) {
            String username = authentication.getName();
            if (username != null) {
                CartEntity cart = cartService.getOrCreateCartForUser(username);
                Optional<ProductEntity> product = productService.getProductById(cartItemRequest.getProductId());
                CartItemEntity cartItem = new CartItemEntity();
                cartItem.setCart(cart);
                cartItem.setProduct(product.get());
                cartItem.setQuantity(cartItemRequest.getQuantity());
                CartItemEntity cartItemEntity = cartItemRepository.save(cartItem);
                System.out.println(cartItemEntity);
            }
            return ResponseEntity.ok("The product has been added to the cart.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No permissions.");
        }
    }
}
