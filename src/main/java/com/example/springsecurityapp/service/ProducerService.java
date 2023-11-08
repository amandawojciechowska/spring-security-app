package com.example.springsecurityapp.service;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.model.ProducerTo;

import java.util.List;

public interface ProducerService {

    List<ProducerTo> getAllProducers();

    ProducerEntity getOrCreateProducer(String name);

}
