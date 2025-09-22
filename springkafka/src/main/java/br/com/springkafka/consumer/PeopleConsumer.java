package br.com.springkafka.consumer;

import br.com.springkafka.People;
import br.com.springkafka.domain.Book;
import br.com.springkafka.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class PeopleConsumer {

    private PeopleRepository peopleRepository;

    @KafkaListener(topics = "${topic.name}")
    public void consumer(ConsumerRecord<String, People> record, Acknowledgment ack) {
        People people = record.value();

        log.info("Mensagem Consumida: {}", people);

        br.com.springkafka.domain.People peopleEntity = new br.com.springkafka.domain.People();

        peopleEntity.setId(peopleEntity.getId());
        peopleEntity.setCpf(people.getCpf().toString());
        peopleEntity.setName(people.getName().toString());
        peopleEntity.setBooks(
                people.getBooks().stream()
                        .map(book ->
                                Book.builder().people(peopleEntity).name(book.toString()).build())
                        .collect(Collectors.toList()));

        log.info("Consumindo pessoa com id={} cpf={} name={}", people.getId(), people.getCpf(), people.getName());

        peopleRepository.save(peopleEntity);

        ack.acknowledge(); // Para falar pro kafka tirar a mensagem, da um "como lida"
    }
}