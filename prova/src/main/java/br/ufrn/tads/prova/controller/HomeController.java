package br.ufrn.tads.prova.controller;


import br.ufrn.tads.prova.domain.dto.YatchDTO;
import br.ufrn.tads.prova.servicy.HomeServicy;
import br.ufrn.tads.prova.servicy.YatchServicy;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("Yatch", yatchServicy.getAllOnlyVisibleYatchs());
        model.addAttribute("toCart",homeServicy.getProductsAmountToCart());
        model.addAttribute("products",homeServicy.getProductsToCart());
        model.addAttribute("subtotal",homeServicy
                                                    .getActualUser()
                                                    .getProduct()
                                                    .getSubTotal());

        return "home/index";
    }


    @GetMapping("blank")
    public String getBlank(){
        return "home/blank";
    }


    @GetMapping("checkout")
    public String getCheckout(Model model){
        model.addAttribute("toCart",homeServicy.getProductsAmountToCart());
        model.addAttribute("products",homeServicy.getProductsToCart());
        model.addAttribute("subtotal",homeServicy
                .getActualUser()
                .getProduct()
                .getSubTotal());
        return "home/checkout";
    }

    @PostMapping("/addToCheckout")
    public String addToCheckout(@Valid YatchDTO yatchDTO, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/";
        }
        homeServicy.addProductToCart(yatchDTO);
        return "redirect:/";
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
