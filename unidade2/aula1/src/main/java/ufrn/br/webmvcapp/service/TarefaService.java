package ufrn.br.webmvcapp.service;


import org.springframework.stereotype.Service;
import ufrn.br.webmvcapp.domain.Tarefa;
import ufrn.br.webmvcapp.repository.TarefaRepository;

import java.util.List;

@Service
public class TarefaService {

    TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Tarefa salvar(String descricao, Boolean ativo){
        Tarefa tarefa = new Tarefa(0l, descricao, ativo);
        return repository.save(tarefa);
    }

    public List<Tarefa> listarTodos(){
        return repository.findAll();
    }

}
