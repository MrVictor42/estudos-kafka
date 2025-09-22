package br.com.springkafka.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @ManyToOne
    private People people;
}