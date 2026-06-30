package br.ufrn.tads.prova.controller;


import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.servicy.HomeServicy;
import br.ufrn.tads.prova.servicy.YatchServicy;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@RequestMapping("/")
@Controller
public class HomeController {

    private final YatchServicy yatchServicy;
    private final HomeServicy homeServicy;


    public HomeController(YatchServicy yatchServicy,
                          HomeServicy homeServicy){

        this.yatchServicy = yatchServicy;
        this.homeServicy = homeServicy;
    }


    @GetMapping
    public String getHome(Model model){
        model = homeServicy.returnReadyModel(model);
        model.addAttribute("Yatch", yatchServicy.getAllOnlyVisibleYatchs());
        return "home/index";
    }


    @GetMapping("blank")
    public String getBlank(){
        return "home/blank";
    }


    @GetMapping("checkout")
    public String getCheckout(Model model){
        model = homeServicy.returnReadyModel(model);
        return "home/checkout";
    }

    @PostMapping("/addToCheckout/{id}")
    public String addToCheckout(@PathVariable UUID id){
        homeServicy.addProductToCart(id);
        return "redirect:/";
    }

    @PostMapping("/finalizarCompra")
    public String finalizarCompra(
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        //testando maneira mais limpa
          return homeServicy.placeOrder(redirectAttributes);
        }

    @GetMapping("detalhe")
    public String getProduct(@RequestParam UUID id,
                             Model model) {
        model = homeServicy.returnReadyModel(model);
        Yatch yatch = yatchServicy.getYatchById(id);
        model.addAttribute("yatch", yatch);

        return "home/detalhe";
    }


    @GetMapping("store")
    public String getStore(){
        return "home/store";
    }
}
