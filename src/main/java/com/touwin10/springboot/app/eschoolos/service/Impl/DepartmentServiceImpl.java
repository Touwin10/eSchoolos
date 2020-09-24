package com.touwin10.springboot.app.eschoolos.service.Impl;

import com.touwin10.springboot.app.eschoolos.model.Course;
import com.touwin10.springboot.app.eschoolos.model.Department;
import com.touwin10.springboot.app.eschoolos.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceBaseImpl<Department> {

    private DepartmentRepository repository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Department> searchData(String searchString) {
        if (searchString instanceof String) {
            searchString = searchString.toLowerCase();
        }
        return repository.findAllByNameContainingOrHeadOf( searchString, searchString);
    }

    public Page<Department> searchDataPaged(String searchString, int pageNo) {
        return repository.findAllByNameContainingOrHeadOf(searchString, searchString, PageRequest.of(pageNo, 10, Sort.by("name")));
    }

    @Override
    public Page<Department> getAllDataPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 10, Sort.by("name")));
    }
}
