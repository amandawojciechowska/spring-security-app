package com.example.springsecurityapp.service;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.model.ProductTo;

import java.util.List;

public interface ProductService {

    List<ProductTo> getAllProductsByProducer(ProducerEntity producer);

}
