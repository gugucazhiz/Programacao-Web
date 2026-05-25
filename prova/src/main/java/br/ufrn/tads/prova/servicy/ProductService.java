package br.ufrn.tads.prova.servicy;


import br.ufrn.tads.prova.domain.model.Person;
import br.ufrn.tads.prova.domain.model.Product;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.exception.RecursoNaoEncontradoException;
import br.ufrn.tads.prova.repository.ProductRepository;
import br.ufrn.tads.prova.repository.YatchRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    final private YatchRepository yatchRepository;
    final private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository,
                          YatchRepository yatchRepositor){
        this.productRepository = productRepository;
        this.yatchRepository = yatchRepositor;
    }

    @Transactional
    public Product addYatchToBuy(UUID id, Person person){
        Yatch yatch = yatchRepository
                .findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Iate não encontrado"
                        ));
        Product product = productRepository.findProductById(person.getProduct().getId());
        product.setYatchsToBuy(yatch);

        return productRepository.save(product);
    }
}
