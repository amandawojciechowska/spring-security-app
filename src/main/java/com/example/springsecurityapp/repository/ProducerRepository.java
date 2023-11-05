package com.example.springsecurityapp.repository;

import com.example.springsecurityapp.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<ProducerEntity, Long> {

}
