package br.ufrn.tads.prova.config;

import br.ufrn.tads.prova.exception.RecursoNaoEncontradoException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Intercepta RecursoNaoEncontradoException em qualquer controller
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public String handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex,
                                             Model model) {
        model.addAttribute("erro", ex.getMessage());
        return "erro-404"; // → templates/erro-404.html  (página de erro amigável)
    }
}
