package com.example.springsecurityapp.service;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.model.ProductTo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Page<ProductTo> getAllProductsPaginated(int page, int size);

    List<ProductTo> getAllProductsByProducer(ProducerEntity producer);

    public List<ProductTo> searchProductsByName(String name);

}
