package com.touwin10.springboot.app.eschoolos.service.Impl;

import com.touwin10.springboot.app.eschoolos.model.Professor;
import com.touwin10.springboot.app.eschoolos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl  extends ServiceBaseImpl<Professor> {

    private ProfessorRepository repository;
    @Autowired
    public ProfessorServiceImpl(ProfessorRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Professor> searchData(String searchString) {
        if (searchString instanceof String) {
            searchString = searchString.toLowerCase();
        }
        return repository.findAllByProfessorNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(searchString, searchString, searchString, searchString);
    }

    public Page<Professor> searchDataPaged(String searchString, int pageNo) {
        return repository.findAllByProfessorNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(searchString, searchString, searchString, searchString, PageRequest.of(pageNo, 10, Sort.by("professorNumber")));
    }

    @Override
    public Page<Professor> getAllDataPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 10, Sort.by("professorNumber")));
    }
}
