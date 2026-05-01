package ufrn.br.webmvcapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ufrn.br.webmvcapp.domain.User;
import ufrn.br.webmvcapp.domain.UserDTO;
import ufrn.br.webmvcapp.service.UserService;

@Controller
@RequestMapping("/mvc")
public class MvcController {
    UserService service;
    public MvcController(UserService service){
        this.service = service;
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
    @GetMapping("/tarefas")
    public String getNewTarefa(){
        return "newTask";
    }

    @PostMapping("/tarefas")
    public String postNewTarefa(@Valid UserDTO userdto){//when the sping tryes to submit he will try
        //to bind in that entity
        User user = new User(userdto.getFirstName() , userdto.getLastName());
        System.out.println(user.isAdmin() +"\n User name: "+user.getFirstName());
        return "redirect:/mvc/tarefas";
    }
}
