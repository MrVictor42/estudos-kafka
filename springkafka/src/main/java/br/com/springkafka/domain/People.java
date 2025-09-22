package br.com.springkafka.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String cpf;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<Book> books;
}