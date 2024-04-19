package com.sport.service;

import com.sport.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    Page<CategoryEntity> findAll(Integer pageNo, Integer quantity);
    List<CategoryEntity> findAll();
    void handleAddCategory(CategoryEntity categoryEntity);
    CategoryEntity findByCategoryId(Long id);
    void handleEditCategory(CategoryEntity categoryEntity);
    void deleteByCategoryId(Long id);
}
