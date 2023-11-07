package com.example.springsecurityapp.model;

import lombok.Builder;

@Builder
public record ProductTo(String name, ProducerTo producer) {

}
