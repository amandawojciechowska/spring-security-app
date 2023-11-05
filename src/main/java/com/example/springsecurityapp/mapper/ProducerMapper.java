package com.example.springsecurityapp.mapper;

import com.example.springsecurityapp.entity.ProducerEntity;
import com.example.springsecurityapp.model.ProducerTo;

import java.util.List;
import java.util.stream.Collectors;

public class ProducerMapper {

    public static ProducerTo toProducerTo(ProducerEntity producerEntity) {
        if (producerEntity == null) {
            return null;
        }
        return ProducerTo.builder().name(producerEntity.getName()).build();
    }

    public static List<ProducerTo> map2Tos(List<ProducerEntity> producerEntities) {
        return producerEntities.stream()
                .map(ProducerMapper::toProducerTo)
                .collect(Collectors.toList());
    }

}
