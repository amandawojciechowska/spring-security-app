package com.example.springsecurityapp.mapper;

import com.example.springsecurityapp.entity.ProductEntity;
import com.example.springsecurityapp.model.ProductTo;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductTo mapProductEntityToTo(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return ProductTo.builder().name(productEntity.getName()).build();
    }

    public static List<ProductTo> mapProductEntities2Tos(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductMapper::mapProductEntityToTo)
                .collect(Collectors.toList());
    }

}
