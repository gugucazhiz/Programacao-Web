package br.com.ufrn.tads.pw2.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("Hello, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
