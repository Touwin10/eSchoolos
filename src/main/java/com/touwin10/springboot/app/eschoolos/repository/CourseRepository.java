package com.touwin10.springboot.app.eschoolos.repository;

import com.touwin10.springboot.app.eschoolos.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface CourseRepository  extends JpaRepository<Course, Integer> {
    List<Course> findAllByCourseCodeContainingOrNameContaining(String code, String name);
    Page<Course> findAllByCourseCodeContainingOrNameContaining(String code, String name, Pageable pageable);
}
