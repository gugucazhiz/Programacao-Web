package br.ufrn.grcp.controller;


import br.ufrn.grcp.entity.Student;
import br.ufrn.grcp.entity.StudentDTO;
import br.ufrn.grcp.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController{
    private final StudentService studentService;


    @PostMapping("")
    public CompletableFuture<ResponseEntity<Student>> postRecord(@RequestBody StudentDTO studentDTO){
        return studentService.save(studentDTO).thenApply(ResponseEntity::ok);
    }

    @GetMapping("")
    public CompletableFuture<ResponseEntity<List<Student>>> getAll(){
        return studentService.getAll().thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Student>> postUpdate(
            @RequestBody StudentDTO studentDTO,
            @PathVariable Long id){
        return studentService.update(studentDTO,id).thenApply(ResponseEntity::ok);
    }
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Student>> postDelete(
            @RequestBody StudentDTO studentDTO,
            @PathVariable Long id){
        return studentService.update(studentDTO,id).thenApply(ResponseEntity::ok);
    }
}
