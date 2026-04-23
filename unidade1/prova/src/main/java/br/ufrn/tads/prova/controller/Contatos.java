package br.ufrn.tads.prova.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class Contatos {
    
    @RequestMapping("/lead")
    public void getEmail(HttpServletRequest request, HttpServletResponse response)throws IOException{
        String metodo = request.getMethod();
        System.out.println(metodo);
        if("GET".equalsIgnoreCase(metodo)){
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("<h1> Entrada De email <h1/>");   
            response.getWriter().println("<form action='/lead' method='POST'> <input placeholder= 'Email'> <button type='submit'>Confirmar</button></form>");

        }
        else if("POST".equalsIgnoreCase(metodo)){
            response.getWriter().println("<h1>Voce veio para o post pelo padrao PRG<h1/>");   
        }
    }
}