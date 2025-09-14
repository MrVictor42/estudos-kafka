package com.live.kafka.consumer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //usa para fazer get e set
@NoArgsConstructor
public class CarDTO {

    private String id;
    private String model;
    private String color;
}