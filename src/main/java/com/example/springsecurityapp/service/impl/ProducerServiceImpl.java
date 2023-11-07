package com.example.springsecurityapp.service.impl;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.mapper.ProducerMapper;
import com.example.springsecurityapp.model.ProducerTo;
import com.example.springsecurityapp.repository.ProducerRepository;
import com.example.springsecurityapp.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProducerTo> getAllProducers() {
        List<ProducerEntity> producers = producerRepository.findAll();
        return ProducerMapper.mapProducerEntities2Tos(producers);
    }

}
