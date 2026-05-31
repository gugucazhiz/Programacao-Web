package br.ufrn.grcp.service;


import br.ufrn.grcp.entity.Student;
import br.ufrn.grcp.entity.StudentDTO;
import br.ufrn.grcp.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class StudentServiceIMP implements StudentService{
    private final StudentRepository studentRepository;

    @Async("asyncExecuter")
    @Override
    public CompletableFuture<Student> save(StudentDTO studentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setStatus(true);
        return CompletableFuture.completedFuture(
                studentRepository.save(student));
    }

    @Async("asyncExecuter")
    @Override
    public CompletableFuture<Student> getOneById(Long id) {
        return CompletableFuture.completedFuture(
                studentRepository.getById(id));
    }

    @Async("asyncExecuter")
    @Override
    public CompletableFuture<List<Student>> getAll() {
        return CompletableFuture.completedFuture(
                studentRepository.findAll()
        );
    }

    @Async("asyncExecuter")
    @Override
    public CompletableFuture<Student> update(StudentDTO studentDTO, Long id) {
        Student findStudent = studentRepository.getById(id);
        if(findStudent.getId()!= null){
            ModelMapper modelMapper = new ModelMapper();
            Student updateStudent = modelMapper.map(findStudent, Student.class);
            return CompletableFuture.completedFuture(
                    studentRepository.save(updateStudent)
            );
        }
        return null;
    }

    @Async("asyncExecuter")
    @Override
    public CompletableFuture<Student> delete(Long id) {
        Student findStudent = studentRepository.getById(id);
        if(findStudent.getId()!=null){
            findStudent.setStatus(false);
            return CompletableFuture.completedFuture(studentRepository.save(findStudent));
        }
        return null;
    }
}
