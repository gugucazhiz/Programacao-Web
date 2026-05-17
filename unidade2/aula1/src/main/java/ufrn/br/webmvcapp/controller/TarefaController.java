package ufrn.br.webmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ufrn.br.webmvcapp.service.TarefaService;

@RequestMapping("/dashboard")
@Controller
public class TarefaController {

    TarefaService service;

    public TarefaController(TarefaService tarefaService){
        this.service = tarefaService;
    }


    @GetMapping("/lista")
    public String getDashboardPage(Model model){
        model.addAttribute("username", "João Miguel");

        return "dashboard";
    }
}
