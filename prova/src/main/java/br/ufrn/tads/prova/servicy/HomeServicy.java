package br.ufrn.tads.prova.servicy;


import br.ufrn.tads.prova.domain.dto.YatchDTO;
import br.ufrn.tads.prova.domain.model.Person;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.repository.YatchRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HomeServicy {

    final private YatchRepository yatchRepository;
    final private ProductService productService;
    final private Person loggedPerson;

    public HomeServicy(YatchRepository yatchRepository,
                       ProductService productService,
                       Person loggedPerson, Person person){
        this.yatchRepository = yatchRepository;
        this.productService = productService;
        this.loggedPerson = loggedPerson;
    }


    public void addProductToCart(YatchDTO yatchDTO){
        Yatch yatch= yatchRepository.findById(yatchDTO.getId());
        productService.addYatchToBuy(yatch,loggedPerson);
    }
}
