package br.ufrn.tads.prova.servicy;

import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.exception.RecursoNaoEncontradoException;
import br.ufrn.tads.prova.repository.YatchRepository;
import lombok.Getter;
import org.hibernate.type.descriptor.jdbc.LocalDateTimeJdbcType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class YatchServicy {
    final YatchRepository yatchRepository;

    public YatchServicy(YatchRepository yatchRepository){
        this.yatchRepository = yatchRepository;
    }


    public void restore(UUID id){
        Yatch yatch =yatchRepository.getById(id);
        yatch.setIsDeleted(null);
        yatchRepository.save(yatch);
    }

    public void deleteYatch(UUID id){
        Yatch yatch =yatchRepository.getById(id);
        LocalDateTime localDateTime = LocalDateTime.now();
        yatch.setIsDeleted(localDateTime);
        yatchRepository.save(yatch);
    }

    public List<Yatch> getAllYatchs(){
        return yatchRepository.findAll();
    }

    public List<Yatch> getAllOnlyVisibleYatchs(){
        return yatchRepository.findAllByIsDeletedIsNull();
    }

    public Yatch getYatchById(UUID id){
        return yatchRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Iate não encontrado para o ID: " + id
                ));
    }

    public void save(Yatch yatch){
        yatchRepository.save(yatch);
    }

    public String getImgSorted(){
        String imagemSorted = getIMAGENS_DISPONIVEIS
                .get(new Random().nextInt(getIMAGENS_DISPONIVEIS.size()));
        System.out.println(imagemSorted);
        String result = imagemSorted.substring(imagemSorted.lastIndexOf("/") + 1);
        return  result;
    }



    private final List<String> getIMAGENS_DISPONIVEIS = List.of(
            "/img/product01.png",
            "/img/product02.png",
            "/img/product03.png",
            "/img/product04.png",
            "/img/product05.png",
            "/img/product06.png",
            "/img/product07.png",
            "/img/product08.png",
            "/img/product09.png"
    );

}
