package br.com.springkafka.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String cpf;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<Book> books;
}