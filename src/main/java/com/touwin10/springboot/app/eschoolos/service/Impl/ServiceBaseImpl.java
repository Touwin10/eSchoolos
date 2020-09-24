package com.touwin10.springboot.app.eschoolos.service.Impl;

import com.touwin10.springboot.app.eschoolos.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class ServiceBaseImpl<T> implements IService<T> {

    private JpaRepository<T, Integer> repository;

    public ServiceBaseImpl(JpaRepository<T, Integer> repository){
        this.repository = repository;
    }

    @Override
    public List<T> getAllData() {
        return (List<T>) repository.findAll();
    }

    @Override
    public T saveData(T data) {
        return repository.save(data);
    }

    @Override
    public T getDataById(Integer dataId) {
        return repository.findById(dataId).orElse(null);
    }

    @Override
    public void deleteDataById(Integer dataId) {
        repository.deleteById(dataId);
    }

    @Override
    public List<T> searchData(String searchString) {
        return null;
    }

    @Override
    public Page<T> getAllDataPaged(int pageNo) {
        return null;
    }

    @Override
    public Page<T> searchDataPaged(String searchString, int pageNo) {
        return null;
    }
}
