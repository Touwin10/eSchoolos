package com.touwin10.springboot.app.eschoolos.service.Impl;

import com.touwin10.springboot.app.eschoolos.model.Asset;
import com.touwin10.springboot.app.eschoolos.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl extends ServiceBaseImpl<Asset> {

    private AssetRepository repository;
    @Autowired
    public AssetServiceImpl(AssetRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Asset> searchData(String searchString) {
        if (searchString instanceof String) {
            searchString = searchString.toLowerCase();
        }
        return repository.findAllByTitleContainingOrSubjectContaining( searchString, searchString);
    }

    public Page<Asset> searchDataPaged(String searchString, int pageNo) {
        return repository.findAllByTitleContainingOrSubjectContaining(searchString, searchString, PageRequest.of(pageNo, 10, Sort.by("title")));
    }

    @Override
    public Page<Asset> getAllDataPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 10, Sort.by("title")));
    }
}
