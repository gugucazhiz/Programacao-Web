package br.ufrn.tads.prova.controller;


import br.ufrn.tads.prova.domain.dto.YatchDTO;
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
    public String salvar(@Valid @ModelAttribute("yatch") YatchDTO yatchdto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        return yatchServicy.save(yatchdto,bindingResult,redirectAttributes);
    }

    @PostMapping("/restore/{id}")
    public String restore(@PathVariable UUID id,
                         RedirectAttributes redirectAttributes) {
        return yatchServicy.restore(id,redirectAttributes);
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id,
                         RedirectAttributes redirectAttributes) {
        return yatchServicy.deleteYatch(id, redirectAttributes);
    }



}
