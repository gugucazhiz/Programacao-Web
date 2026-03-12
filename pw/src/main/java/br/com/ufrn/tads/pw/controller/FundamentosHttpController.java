package br.com.ufrn.tads.pw.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FundamentosHttpController {
    public void gerenciarRotas(HttpServletResponse response, HttpServletRequest request){
        String metodo = request.getMethod();

        if("GET".equalsIgnoreCase(metodo)){
            processarGet(response);
        }else if("POST".equalsIgnoreCase(metodo)){
            processarPost(response,request);
        }else{
            response.setStatus(405);
        }
    }

    private void processarPost(HttpServletResponse response, HttpServletRequest request) {
        String titulo = request.getParameter("titulo");
    }

    private void processarGet(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);

        response.setHeader("Location", "/tarefas");
    }
}
