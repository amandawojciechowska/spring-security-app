package com.example.springsecurityapp.controller;

import com.example.springsecurityapp.entity.CartEntity;
import com.example.springsecurityapp.entity.CartItemEntity;
import com.example.springsecurityapp.entity.ProductEntity;
import com.example.springsecurityapp.exception.NotFoundException;
import com.example.springsecurityapp.model.CartItemRequest;
import com.example.springsecurityapp.model.ProductTo;
import com.example.springsecurityapp.repository.CartItemRepository;
import com.example.springsecurityapp.repository.ProductRepository;
import com.example.springsecurityapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addItemToCart(@RequestBody CartItemRequest cartItemRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")) && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Long productId = cartItemRequest.getProductId();
            Long quantity = cartItemRequest.getQuantity();
            if (username != null) {
                validParametersInRequest(productId, quantity);
                saveProductInCart(username, productId, quantity);
            }
            return ResponseEntity.ok("The product has been added to the cart.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No permissions.");
        }
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCartWithProductsByUsername(@PathVariable("username") String username) {
        if (username == null) {
            throw new IllegalArgumentException("Parameter username must be completed!");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")) && authentication.isAuthenticated()) {
            String nameLoggedUser = authentication.getName();
            if (nameLoggedUser.equals(username)) {
                List<ProductTo> products = cartService.getProductsFromCartForUser(username);
                if (!products.isEmpty()) {
                    return ResponseEntity.ok(products);
                } else {
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no products in your cart.");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have access to this shopping cart!");
    }

    private void validParametersInRequest(Long productId, Long quantity) {
        if (productId == null) {
            throw new IllegalArgumentException("Parameter productId must be completed!");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Parameter quantity must be completed!");
        }
    }

    private void saveProductInCart(String username, Long productId, Long quantity) {
        CartEntity cart = cartService.getOrCreateCartForUser(username);
        Optional<ProductEntity> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found with ID: " + productId);
        }

        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart);
        cartItem.setProduct(product.get());
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
}
