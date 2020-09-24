package com.touwin10.springboot.app.eschoolos.repository;

import com.touwin10.springboot.app.eschoolos.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository  extends JpaRepository<Professor, Integer> {
    List<Professor> findAllByProfessorNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(String ProfessorNumber, String first, String middle, String last);

    Page<Professor> findAllByProfessorNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(String ProfessorNumber, String first, String middle, String last, Pageable pageable);

}