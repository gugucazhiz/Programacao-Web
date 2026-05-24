package br.ufrn.tads.prova.servicy;


import br.ufrn.tads.prova.domain.model.Person;
import br.ufrn.tads.prova.domain.model.Product;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    final private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product addYatchToBuy(Yatch yatch,Person person){
        if(person.getProduct() == null){
            Product product = new Product(yatch);
            person.setProduct(product);
            return productRepository.save(product);
        }
        Product product = productRepository.findProductById(person.getProduct().getId());
        product.setYatchsToBuy(yatch);

        return productRepository.save(product);
    }
}
