package com.sport.service.impl;

import com.sport.entity.ProductEntity;
import com.sport.repository.ProductRepository;
import com.sport.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductEntity> findAll(Integer pageNo, Integer quantity) {
        Pageable pageable = PageRequest.of(pageNo - 1, quantity);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductEntity> findByCategoryName(Integer pageNo, Integer quantity, String name) {
        Pageable pageable = PageRequest.of(pageNo - 1, quantity);
        return productRepository.findByCategoryEntitiesCategoryNameLike(name, pageable);
    }
}
