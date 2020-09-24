package com.touwin10.springboot.app.eschoolos.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IService<T> {
    public abstract List<T> getAllData();

    public abstract T saveData(T data);

    public abstract T getDataById(Integer dataId);

    public abstract void deleteDataById(Integer dataId);

    public abstract List<T> searchData(String searchString);

    public abstract Page<T> getAllDataPaged(int pageNo);

    public abstract Page<T> searchDataPaged(String searchString, int pageNo);
}
