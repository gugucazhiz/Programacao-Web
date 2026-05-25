package br.ufrn.tads.prova.servicy;

import br.ufrn.tads.prova.domain.dto.YatchDTO;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.exception.RecursoNaoEncontradoException;
import br.ufrn.tads.prova.repository.YatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public String save(YatchDTO yatchDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){

        Yatch yatch = new Yatch(yatchDTO.getId(),
                yatchDTO.getName(),
                yatchDTO.getColor(),
                yatchDTO.getImagem(),
                yatchDTO.getCodProduct(),
                yatchDTO.getPrice());

        if (bindingResult.hasErrors()) {
            return yatch.getId() == null ? "/admin/cadastro" : "/admin/editar";
        }

        boolean isNovo = (yatch.getId() == null);
        yatchRepository.save(yatch);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                isNovo ? "Iate cadastrado com sucesso!" : "Iate atualizado com sucesso!"
        );


        return "redirect:/admin";

    }

    public String getImgSorted(){
        String imagemSorted = getIMAGENS_DISPONIVEIS
                .get(new Random().nextInt(getIMAGENS_DISPONIVEIS.size()));
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
