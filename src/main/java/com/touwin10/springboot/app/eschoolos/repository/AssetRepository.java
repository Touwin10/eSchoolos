package com.touwin10.springboot.app.eschoolos.repository;

import com.touwin10.springboot.app.eschoolos.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    List<Asset> findAllByTitleContainingOrSubjectContaining(String title, String subject);
    Page<Asset> findAllByTitleContainingOrSubjectContaining(String title, String subject, Pageable pageable);
}