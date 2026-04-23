package br.com.ufrn.tads.pw2.controller;

import java.io.IOException;

import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class PreferenciasController {

 @RequestMapping("/preferencias/tema")
    
    public void alternarTema(HttpServletRequest request, HttpServletResponse response)throws IOException{
        Cookie[] cookies = request.getCookies();
        String temaAtual = "light";
        
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if("app_tema".equals(cookie.getName())) {
                    temaAtual = cookie.getValue();
                    break;
                }
            }
        }
        try {
            response.getWriter().println("Tema altreado com sucesso");
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        String novoTema = temaAtual.equals("light") ? "dark" : "light";
        Cookie c = new Cookie("app_tema", novoTema);
        c.setMaxAge(60 * 60 * 24 * 30); // 30 dias

        c.setHttpOnly(true);
        c.setPath("/");

        response.addCookie(c);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String corFundo = novoTema.equals("dark") ? "#1e1e1e" : "#ffffff";
        String corTexto = novoTema.equals("dark") ? "#ffffff" : "#000000";
        response.getWriter().println("<html style='background-color: " + corFundo + "; color: " + corTexto + ";'>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Sistema de Gestão de Tarefas</h1>");
        response.getWriter().println("<p>O tema foi gravado com sucesso no seu navegador.</p>");
        response.getWriter().println("<p>Tema atual: <strong>" + novoTema.toUpperCase() + "</strong></p>");
        // O botão é apenas um link GET para a mesma rota, que executará a inversão novamente
        response.getWriter().println("<a href='/preferencias/tema'><button>Alternar Tema</button></a>");

        response.getWriter().println("</body></html>");
        response.getWriter().flush();
    }
}