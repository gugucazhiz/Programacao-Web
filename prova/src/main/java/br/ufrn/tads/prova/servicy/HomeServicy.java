package br.ufrn.tads.prova.servicy;


import br.ufrn.tads.prova.domain.dto.YatchDTO;
import br.ufrn.tads.prova.domain.model.Person;
import br.ufrn.tads.prova.domain.model.Product;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.repository.PersonRepository;
import br.ufrn.tads.prova.repository.ProductRepository;
import br.ufrn.tads.prova.repository.YatchRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServicy {
    private final PersonRepository personRepository;
    private final  YatchRepository yatchRepository;
    private final  ProductService productService;
    private final Person loggedPerson;
    final private ProductRepository productRepository;

    public HomeServicy(YatchRepository yatchRepository,
                       ProductService productService,
                       Person loggedPerson,
                       PersonRepository personRepository,
                       ProductRepository productRepository){

        this.yatchRepository = yatchRepository;
        this.productService = productService;
        this.loggedPerson = loggedPerson;
        this.personRepository = personRepository;
        this.productRepository = productRepository;
    }


    public void addProductToCart(YatchDTO yatchDTO){
        Yatch yatch= yatchRepository.findById(yatchDTO.getId());
        Person person = getActualUser();
        productService.addYatchToBuy(yatch,person);
    }

    public int getProductsAmountToCart(){
        Person person = getActualUser();
        return person.getProduct() ==null ? 0 : person.getProduct()
                                                      .getYatchsToBuy()
                                                      .size();
    }
    public List<Yatch> getProductsToCart() {
        Person person = getActualUser();
        if(person.getProduct() != null){

            person.getProduct().setSubTotal(
                    person.getProduct().getYatchsToBuy()
                    .stream()
                    .map(Yatch::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add))
            ;

            return person.getProduct().getYatchsToBuy();
        }
        List<Yatch> yatches = new ArrayList<>();
        return yatches;
    }

    public Person getActualUser(){
        Person person = personRepository.getByid(loggedPerson.getId());
        if(person.getProduct() == null){
            Product product = new Product();
            product.setPerson(person);
            person.setProduct(product);
            productRepository.save(product);
        }
        return person;
    }
}
