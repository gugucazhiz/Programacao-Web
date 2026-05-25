package br.ufrn.tads.prova.config;

import br.ufrn.tads.prova.exception.RecursoNaoEncontradoException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public String handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex,
                                             Model model) {
        model.addAttribute("erro", ex.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAcessDenied(AccessDeniedException ex,
                                             Model model) {
        model.addAttribute("erro", ex.getMessage());
        return "/error/403";
    }
}
