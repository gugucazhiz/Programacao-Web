package br.ufrn.tads.prova.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class Cotacao {
    @RequestMapping("/cotacao")
    public void getCotacao(HttpServletRequest request, HttpServletResponse response)throws IOException{
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h1> hello world <h1/>");
        response.getWriter().println("<h1>Cotacao<h1/>");
    }
}
