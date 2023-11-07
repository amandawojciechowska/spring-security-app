package com.example.springsecurityapp.controller;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.exception.NotFoundException;
import com.example.springsecurityapp.model.ProductTo;
import com.example.springsecurityapp.repository.ProducerRepository;
import com.example.springsecurityapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProducerRepository producerRepository;

    @GetMapping("/{producerId}")
    public List<ProductTo> getProductsByProducerId(@PathVariable("producerId") Long producerId) {
        ProducerEntity producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new NotFoundException("Producer not found with ID: " + producerId));
        return productService.getAllProductsByProducer(producer);
    }

}
