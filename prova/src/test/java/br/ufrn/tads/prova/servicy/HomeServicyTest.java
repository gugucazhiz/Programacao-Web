package br.ufrn.tads.prova.servicy;

import br.ufrn.tads.prova.config.YatchDataTest;
import br.ufrn.tads.prova.domain.model.Person;
import br.ufrn.tads.prova.domain.model.Product;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomeServicyTest {

    @InjectMocks
    private HomeServicy homeServicy;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RedirectAttributes redirectAttributes;

    ///verificação de mensagem de erro

    @Test
    @DisplayName("Deve Exibir mensagem de erro apos finalizarCompra")
    void deveExibirMensagemDeErroCompra() throws Exception{
        Product product = new Product();
        product.mockYatchsToBuy(new ArrayList<>());

        Person person = new Person();
        person.setProduct(product);

        HomeServicy spy = Mockito.spy(homeServicy);
        doReturn(person).when(spy).getActualUser();

        String retorno = spy.placeOrder(redirectAttributes);

        assertEquals("redirect:/checkout",retorno);

        verify(redirectAttributes).addFlashAttribute(
                "error",
                "Seu carrinho está vazio."
        );
        verify(productRepository,never()).save(any());
    }

    @Test
    @DisplayName("Deve Exibir Mensagem De Sucesso ao Comprar")
    void deveExibirMensagemDeSucessoCompra() throws Exception{
        Product product = new Product();
        ArrayList<Yatch> yatches = new ArrayList<>();
        yatches.add(YatchDataTest.createTestYatch());
        product.mockYatchsToBuy(yatches);

        Person person = new Person();
        person.setProduct(product);
        HomeServicy spy = Mockito.spy(homeServicy);
        doReturn(person).when(spy).getActualUser();

        String retorno = spy.placeOrder(redirectAttributes);

        assertEquals("redirect:/",retorno);

        verify(redirectAttributes).addFlashAttribute(
                "success",
                "Compra realizada com sucesso!"
        );
        verify(productRepository).save(product);
        assertTrue(product.getYatchsToBuy().isEmpty());
    }
}
