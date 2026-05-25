package br.ufrn.tads.prova.repository;

import br.ufrn.tads.prova.domain.model.Yatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface YatchRepository extends JpaRepository<Yatch,Long> {

    List<Yatch> findAllByIsDeletedIsNull();

    Yatch findById(UUID id);

    Yatch getById(UUID id);
}
