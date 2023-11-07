package com.example.springsecurityapp.controller;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.exception.NotFoundException;
import com.example.springsecurityapp.model.ProductTo;
import com.example.springsecurityapp.repository.ProducerRepository;
import com.example.springsecurityapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProducerRepository producerRepository;

    @GetMapping
    public Page<ProductTo> getAllProducts(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return productService.getAllProductsPaginated(page, size);
    }

    @GetMapping("/{producerId}")
    public List<ProductTo> getProductsByProducerId(@PathVariable("producerId") Long producerId) {
        ProducerEntity producer = producerRepository.findById(producerId).orElseThrow(() -> new NotFoundException("Producer not found with ID: " + producerId));
        return productService.getAllProductsByProducer(producer);
    }

    @GetMapping("/search")
    public List<ProductTo> searchProducts(@RequestParam(name = "name") String name) {
        return productService.searchProductsByName(name);
    }

}
