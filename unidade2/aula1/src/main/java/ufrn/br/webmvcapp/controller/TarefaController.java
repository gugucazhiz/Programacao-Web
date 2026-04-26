package ufrn.br.webmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ufrn.br.webmvcapp.domain.User;
import ufrn.br.webmvcapp.service.TarefaService;

@Controller
public class TarefaController {

    TarefaService service;

    public TarefaController(TarefaService tarefaService){
        this.service = tarefaService;
    }

    @GetMapping("/dashboard/lista")
    public String getDashboardPage(Model model){
        model.addAttribute("username", "João Miguel");

        return "dashboard";
    }

    //um jeito de se fazer:
    /*
    @PostMapping("/mvc/tarefas")
    public String postNewTarefa(@RequestParam String firstName){
        System.out.print(firstName);
        return "newTask";
    }
    */

    //The other way is using data biding, and to use this
    // u need a model, in that case i will use the model that i created
    // "User.java"
    @PostMapping("/mvc/tarefas")
    public String postNewTarefa(User user){//when the sping tryes to submit he will try
                                            //to bind in that entity
        System.out.println(user);
        return "newTask";
    }
}
