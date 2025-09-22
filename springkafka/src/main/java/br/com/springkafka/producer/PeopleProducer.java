package br.com.springkafka.producer;

import br.com.springkafka.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PeopleProducer {

    private final String topicName;
    private final KafkaTemplate<String, People> kafkaTemplate; //esse people é o do avro, que para rodar, tem que rodar um mvn clean install

    public PeopleProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, People> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(People people) {
        kafkaTemplate.send(topicName, people)
            .thenAccept(result -> log.info("Mensagem Enviada Com Sucesso! {}", result.getRecordMetadata()))
            .exceptionally(ex -> {
                log.error("Falha Ao Enviar Mensagem: {}", ex.getMessage());
                return null;
            });
    }
}