package ufrn.br.webmvcapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ufrn.br.webmvcapp.domain.User;
import ufrn.br.webmvcapp.domain.UserDTO;
import ufrn.br.webmvcapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    UserService service;
    public UsersController(UserService userService){
        this.service = userService;
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

    @GetMapping({"/list", "/"})
    public String getUsersList(Model model){
        List<User> users = service.listUser();
        model.addAttribute("users",users);
        return  "users";
    }

    @GetMapping("/create")
    public String getNewUser(){
        return "newUsers";
    }

    @PostMapping("/create")
    public String postNewUser(@Valid UserDTO userdto, BindingResult result){//when the sping tryes to submit he will try
        //to bind in that entity
        if(result.hasErrors()){
            return "newUsers";
        }
        User user = new User(userdto.getFirstName() , userdto.getLastName());
        service.salvar(user);
        System.out.println(user.isAdmin() +"\n User name: "+user.getFirstName());
        return "redirect:/users/list";
    }
}
