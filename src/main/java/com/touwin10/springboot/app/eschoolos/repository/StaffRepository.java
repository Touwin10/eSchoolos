package com.touwin10.springboot.app.eschoolos.repository;

import com.touwin10.springboot.app.eschoolos.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository  extends JpaRepository<Staff, Integer> {
    List<Staff> findAllByFirstNameContainingOrMiddleNameContainsOrLastNameContaining(String first, String middle, String last);

    Page<Staff> findAllByFirstNameContainingOrMiddleNameContainsOrLastNameContaining(String first, String middle, String last, Pageable pageable);

}