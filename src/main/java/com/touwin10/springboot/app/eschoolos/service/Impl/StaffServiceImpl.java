package com.touwin10.springboot.app.eschoolos.service.Impl;

import com.touwin10.springboot.app.eschoolos.model.Professor;
import com.touwin10.springboot.app.eschoolos.model.Staff;
import com.touwin10.springboot.app.eschoolos.repository.ProfessorRepository;
import com.touwin10.springboot.app.eschoolos.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl extends ServiceBaseImpl<Staff> {

    private StaffRepository repository;
    @Autowired
    public StaffServiceImpl(StaffRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Staff> searchData(String searchString) {
        if (searchString instanceof String) {
            searchString = searchString.toLowerCase();
        }
        return repository.findAllByFirstNameContainingOrMiddleNameContainsOrLastNameContaining(searchString, searchString, searchString);
    }

    public Page<Staff> searchDataPaged(String searchString, int pageNo) {
        return repository.findAllByFirstNameContainingOrMiddleNameContainsOrLastNameContaining(searchString, searchString, searchString, PageRequest.of(pageNo, 10, Sort.by("firstName")));
    }

    @Override
    public Page<Staff> getAllDataPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 10, Sort.by("firstName")));
    }
}