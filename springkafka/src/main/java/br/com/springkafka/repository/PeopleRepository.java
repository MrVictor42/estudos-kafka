package br.com.springkafka.repository;

import br.com.springkafka.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<People, UUID> {

}