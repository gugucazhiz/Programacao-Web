package br.ufrn.tads.prova.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping
    public String getHome(){
        return "home/index";
    }
    @GetMapping("blank")
    public String getBlank(){
        return "home/blank";
    }
    @GetMapping("checkout")
    public String getCheckout(){
        return "home/checkout";
    }
    @GetMapping("product")
    public String getProduct(){
        return "home/product";
    }
    @GetMapping("store")
    public String getStore(){
        return "home/store";
    }
}
