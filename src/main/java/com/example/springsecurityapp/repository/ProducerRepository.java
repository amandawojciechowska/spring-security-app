package com.example.springsecurityapp.repository;

import com.example.springsecurityapp.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRepository extends JpaRepository<ProducerEntity, Long> {

    Optional<ProducerEntity> findByName(String name);

}
