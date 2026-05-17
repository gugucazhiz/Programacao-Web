package ufrn.br.webmvcapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String getMenuPage(){

        return "menu";
    }
}
