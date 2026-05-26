package br.ufrn.tads.prova.repository;


import br.ufrn.tads.prova.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    Person getByid(UUID id);
}
