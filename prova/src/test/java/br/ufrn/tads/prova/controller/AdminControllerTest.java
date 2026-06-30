package br.ufrn.tads.prova.controller;

import br.ufrn.tads.prova.config.YatchDataTest;
import br.ufrn.tads.prova.domain.dto.YatchDTO;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.servicy.HomeServicy;
import br.ufrn.tads.prova.servicy.YatchServicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

        @Autowired
        MockMvc mockMvc;

        @MockitoBean
        private YatchServicy yatchServicy;

        @MockitoBean
        private HomeServicy homeServicy;

        @Test
        @DisplayName("Aferir quantidade Correta De entidades no html")
        void quantidadeCorretaDeYatchs() throws Exception{
            when(yatchServicy.getAllYatchs())
                    .thenReturn(YatchDataTest.CreateTestYatchs());

            MvcResult result= mockMvc.perform(get("/admin"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("admin/admin"))
                                .andReturn();

            ModelAndView mav = result.getModelAndView();
            List<Yatch> yatchsNaView = (List<Yatch>) mav.getModel().get("Yatch");
            assertThat(yatchsNaView).hasSize(3);
        }

        @Test
        @DisplayName("Deve Proibir Cadastro de itens com campos vazios")
        void proibirCadastroComCamposVazios() throws Exception{
                when(yatchServicy.getImgSorted())
                        .thenReturn(YatchDataTest.createTestYatch()
                                .getImagem());
                when(yatchServicy.save(any(YatchDTO.class),
                                        any(BindingResult.class),
                                        any(RedirectAttributes.class)))
                        .thenReturn("/admin/cadastro");

                mockMvc.perform(post("/admin/salvar")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("name","")
                                .param("price","")
                                .param("color","")
                                .param("imagem","")
                                .param("codProduct","")
                                .with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(view().name("/admin/cadastro"));
        }

    @Test
    @DisplayName("Deve cadastrar um Yatch com sucesso")
    void deveCadastrarYatchComSucesso() throws Exception {

        when(yatchServicy.save(
                any(YatchDTO.class),
                any(BindingResult.class),
                any(RedirectAttributes.class)))
                .thenReturn("redirect:/admin");

        mockMvc.perform(post("/admin/salvar")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Azimut 60")
                        .param("color", "Branco")
                        .param("imagem", "imagem.jpg")
                        .param("codProduct", "AZ001")
                        .param("price", "850000.00")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"));

        verify(yatchServicy).save(
                any(YatchDTO.class),
                any(BindingResult.class),
                any(RedirectAttributes.class));
    }
}
