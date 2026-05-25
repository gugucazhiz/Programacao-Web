package br.ufrn.tads.prova.controller;


import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.servicy.YatchServicy;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final YatchServicy yatchServicy;

    public AdminController(YatchServicy yatchServicy){
        this.yatchServicy = yatchServicy;
    }

    @GetMapping
    public String getAdminPage(Model model){
        model.addAttribute("Yatch",yatchServicy.getAllYatchs());
        return "/admin/admin";
    }


    @GetMapping("/editar")
    public String editarForm(@RequestParam UUID id, Model model) {
        Yatch yatch = yatchServicy.getYatchById(id);
        model.addAttribute("yatch", yatch);
        return "/admin/editar";
    }

    @GetMapping("/cadastro")
    public String cadastroForm(Model model) {
        Yatch yatch = new Yatch();

        yatch.setImagem(yatchServicy.getImgSorted());

        model.addAttribute("yatch", yatch);
        return "/admin/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("yatch") Yatch yatch,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // Volta ao formulário correto conforme operação
            return yatch.getId() == null ? "/admin/cadastro" : "/admin/editar";
        }

        boolean isNovo = (yatch.getId() == null);
        yatchServicy.save(yatch);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                isNovo ? "Iate cadastrado com sucesso!" : "Iate atualizado com sucesso!"
        );

        return "redirect:/admin";
    }

    @PostMapping("/restore/{id}")
    public String restore(@PathVariable UUID id,
                         RedirectAttributes redirectAttributes) {

        yatchServicy.restore(id);

        redirectAttributes.addFlashAttribute("successMessage",
                "Iate Restaurado com sucesso!");

        return "redirect:/admin";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id,
                         RedirectAttributes redirectAttributes) {

        yatchServicy.deleteYatch(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Iate Deletado com sucesso!");

        return "redirect:/admin";
    }



}
