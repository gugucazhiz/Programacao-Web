package br.ufrn.tads.prova.servicy;

import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.repository.YatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class YatchServicy {
    final YatchRepository yatchRepository;

    public YatchServicy(YatchRepository yatchRepository){
        this.yatchRepository = yatchRepository;
    }


    public List<Yatch> getAllYatchs(){
        return yatchRepository.findAll();
    }

    public List<Yatch> getAllOnlyVisibleYatchs(){
        return yatchRepository.findAllByIsDeletedIsNull();
    }

    public Yatch getYatchById(UUID id){
        return yatchRepository.getById(id);
    }

    public void save(Yatch yatch){
        yatchRepository.save(yatch);
    }


}
