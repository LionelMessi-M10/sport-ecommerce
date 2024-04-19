package com.sport.service.impl;

import com.sport.entity.CategoryEntity;
import com.sport.repository.CategoryRepository;
import com.sport.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryEntity> findAll(Integer pageNo, Integer quantity) {
        Pageable pageable = PageRequest.of(pageNo - 1, quantity);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void handleAddCategory(CategoryEntity categoryEntity) {
        categoryEntity.setActive(1);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity findByCategoryId(Long id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public void handleEditCategory(CategoryEntity categoryEntity) {
        this.categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteByCategoryId(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
