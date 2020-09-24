package com.touwin10.springboot.app.eschoolos.repository;

import com.touwin10.springboot.app.eschoolos.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(String studentNumber, String first, String middle, String last);

    Page<Student> findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(String studentNumber, String first, String middle, String last, Pageable pageable);

}
