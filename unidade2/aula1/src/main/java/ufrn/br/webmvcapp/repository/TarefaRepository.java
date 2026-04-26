package ufrn.br.webmvcapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.webmvcapp.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
}
