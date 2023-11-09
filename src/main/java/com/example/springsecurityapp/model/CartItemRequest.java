package com.example.springsecurityapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@ToString
@EqualsAndHashCode
public final class CartItemRequest {

    @NotNull
    @Positive
    private Long productId;

    @NotNull
    @Positive
    private Long quantity;

    @JsonCreator
    void CreateProductRequest(@JsonProperty("productId") Long productId,
                              @JsonProperty("quantity") Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
