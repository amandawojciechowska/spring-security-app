package com.example.springsecurityapp.controller;

import com.example.springsecurityapp.model.ProducerTo;
import com.example.springsecurityapp.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public List<ProducerTo> getProducers() {
        return producerService.getAllProducers();
    }

}
