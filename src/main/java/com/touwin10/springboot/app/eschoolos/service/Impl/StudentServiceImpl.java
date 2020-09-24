package com.touwin10.springboot.app.eschoolos.service.Impl;

import com.touwin10.springboot.app.eschoolos.model.Student;
import com.touwin10.springboot.app.eschoolos.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceBaseImpl<Student> {

    private StudentRepository repository;
    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Student> searchData(String searchString) {
        if (searchString instanceof String) {
            searchString = searchString.toLowerCase();
        }
        return repository.findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(searchString, searchString, searchString, searchString);
    }

    public Page<Student> searchDataPaged(String searchString, int pageNo) {
        return repository.findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainsOrLastNameContaining(searchString, searchString, searchString, searchString, PageRequest.of(pageNo, 10, Sort.by("studentNumber")));
    }

    @Override
    public Page<Student> getAllDataPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 10, Sort.by("studentNumber")));
    }
}
