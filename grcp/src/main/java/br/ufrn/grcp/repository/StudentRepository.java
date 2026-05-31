package br.ufrn.grcp.repository;


import br.ufrn.grcp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
    Student getById(Long id);
}
