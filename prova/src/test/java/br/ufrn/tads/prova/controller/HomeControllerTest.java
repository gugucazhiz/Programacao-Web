package br.ufrn.tads.prova.controller;

import br.ufrn.tads.prova.config.YatchDataTest;
import br.ufrn.tads.prova.servicy.HomeServicy;
import br.ufrn.tads.prova.servicy.YatchServicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest{

    @Autowired
    MockMvc mockMvc;
    
    @MockitoBean
    private YatchServicy yatchServicy;

    @MockitoBean
    private HomeServicy homeServicy;

    //testando o retorno 200 ao acessar paginas
    @Test
    @DisplayName("Deve navegar para pagina Home")
    void deveNavegarParaPaginaHome() throws Exception{
        when(homeServicy.returnReadyModel(any(Model.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(yatchServicy.getAllOnlyVisibleYatchs())
                .thenReturn(YatchDataTest.CreateTestYatchs());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/index"));
        verify(homeServicy).returnReadyModel(any(Model.class));
        verify(yatchServicy).getAllOnlyVisibleYatchs();
    }

    @Test
    @DisplayName("Deve Navegar para pagina blank")
    void deveNavegarParaPaginaBlank() throws Exception{
        mockMvc.perform(get("/blank"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/blank"));
    }

    @Test
    @DisplayName("Deve Retornar para Checkout Page")
    void deveNavegarParaPaginaCheckout() throws Exception{
        when(homeServicy.returnReadyModel(any(Model.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(get("/checkout"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/checkout"));

        verify(homeServicy).returnReadyModel(any(Model.class));
    }

    @Test
    @DisplayName("Deve Retornar Para DetalhePage")
    void deveNavegarParaPaginaDetalhes() throws Exception{
        UUID id = UUID.randomUUID();
        when(homeServicy.returnReadyModel(any(Model.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(yatchServicy.getYatchById(any(UUID.class)))
                .thenReturn(YatchDataTest.createTestYatch());

        mockMvc.perform(get("/detalhe")
                        .param("id",id.toString())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("home/detalhe"));

        verify(homeServicy).returnReadyModel(any(Model.class));
        verify(yatchServicy).getYatchById(any(UUID.class));
    }




}
