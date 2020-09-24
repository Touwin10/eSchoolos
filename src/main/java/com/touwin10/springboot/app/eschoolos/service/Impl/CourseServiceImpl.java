package com.touwin10.springboot.app.eschoolos.service.Impl;

import com.touwin10.springboot.app.eschoolos.model.Course;
import com.touwin10.springboot.app.eschoolos.model.Professor;
import com.touwin10.springboot.app.eschoolos.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl  extends ServiceBaseImpl<Course>  {

    private CourseRepository repository;
    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Course> searchData(String searchString) {
        if (searchString instanceof String) {
            searchString = searchString.toLowerCase();
        }
        return repository.findAllByCourseCodeContainingOrNameContaining( searchString, searchString);
    }

    public Page<Course> searchDataPaged(String searchString, int pageNo) {
        return repository.findAllByCourseCodeContainingOrNameContaining(searchString, searchString, PageRequest.of(pageNo, 10, Sort.by("name")));
    }

    @Override
    public Page<Course> getAllDataPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 10, Sort.by("courseCode")));
    }
}
