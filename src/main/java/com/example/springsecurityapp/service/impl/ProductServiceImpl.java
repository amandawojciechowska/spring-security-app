package com.example.springsecurityapp.service.impl;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.entity.ProductEntity;
import com.example.springsecurityapp.mapper.ProductMapper;
import com.example.springsecurityapp.model.ProductTo;
import com.example.springsecurityapp.repository.ProductRepository;
import com.example.springsecurityapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductTo> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return ProductMapper.mapProductEntities2Tos(products);
    }

    @Override
    public Page<ProductTo> getAllProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);

        List<ProductTo> productToList = ProductMapper.mapProductEntities2Tos(productPage.getContent());

        return new PageImpl<>(productToList, pageable, productPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductTo> getAllProductsByProducer(ProducerEntity producer) {
        List<ProductEntity> products = productRepository.findByProducer(producer);
        return ProductMapper.mapProductEntities2Tos(products);
    }

}