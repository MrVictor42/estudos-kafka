package br.com.springkafka.controller;

import br.com.springkafka.People;
import br.com.springkafka.producer.PeopleProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {

    private final PeopleProducer peopleProducer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendMessage(@RequestBody PeopleDTO peopleDTO) {
        UUID id = UUID.randomUUID();
        People people = People.newBuilder()
                .setId(id.toString())
                .setName(peopleDTO.getName())
                .setCpf(peopleDTO.getCpf())
                .setBooks(peopleDTO.getBooks().stream().map(book -> (CharSequence) book).collect(Collectors.toList()))
                .build();

        peopleProducer.sendMessage(people);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}