package ufrn.br.aulapratica.service;

import org.springframework.stereotype.Service;
import ufrn.br.aulapratica.model.Tarefa;
import ufrn.br.aulapratica.repository.TarefaRepository;

import java.util.List;

@Service
public class TarefaService {

    private TarefaRepository repository;

    public TarefaService(TarefaRepository repository){
        this.repository = repository;
    }

    public void criarNovaTarefa(String titulo) {
        // Regra de Negócio Centralizada
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa é obrigatório.");
        }

        Tarefa nova = new Tarefa(titulo);
        repository.salvar(nova);
    }

    public List<Tarefa> listarTarefas() {
        return repository.buscarTodas();
    }
}