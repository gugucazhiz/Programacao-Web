package br.ufrn.grcp.service;

import br.ufrn.grcp.entity.Student;
import br.ufrn.grcp.entity.StudentDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface StudentService {


    CompletableFuture<Student> save(StudentDTO studentDTO);
    CompletableFuture<Student> getOneById(Long id);
    CompletableFuture<List<Student>> getAll();
    CompletableFuture<Student> update(StudentDTO studentDTO, Long id);
    CompletableFuture<Student> delete(Long id);
}
