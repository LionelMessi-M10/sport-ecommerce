package com.sport.service;

import com.sport.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Page<ProductEntity> findAll(Integer pageNo, Integer quantity);

    Page<ProductEntity> findByCategoryName(Integer pageNo, Integer quantity, String name);
}
