package com.example.springsecurityapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@ToString
@EqualsAndHashCode
public final class ProductRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String producerName;

    @Positive
    private Long producerId;

    @JsonCreator
    void CreateProductRequest(@JsonProperty("name") String name,
                              @JsonProperty("producerName") String producerName,
                              @JsonProperty("producerId") Long producerId) {
        this.name = name;
        this.producerName = producerName;
        this.producerId = producerId;
    }

}
