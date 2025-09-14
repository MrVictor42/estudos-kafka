package com.live.kafka.producer.controller;

import lombok.Builder;
import lombok.Data;

@Data //usa para fazer get e set
@Builder // usa para deserealizar os objetos
public class CarDTO {

    private String id;
    private String model;
    private String color;
}