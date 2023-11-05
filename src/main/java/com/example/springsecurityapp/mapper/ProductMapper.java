package com.example.springsecurityapp.mapper;

import com.example.springsecurityapp.entity.ProductEntity;
import com.example.springsecurityapp.model.ProductTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity mapProductToToEntity(ProductTo source);

    ProductTo mapProductEntityToTo(ProductEntity source);

}
