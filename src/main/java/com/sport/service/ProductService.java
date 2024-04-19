package com.sport.service;

import com.sport.entity.ProductEntity;
import com.sport.entity.UserEntity;
import com.sport.model.dto.ProductDTO;

import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ProductService {

    Page<ProductEntity> findAll(Integer pageNo, Integer quantity);
    Page<ProductEntity> findByCategoryName(Integer pageNo, Integer quantity, String name);
    void save(MultipartFile[] imageProduct, ProductDTO productDTO, UserEntity userEntity) throws ParseException;
    ProductEntity findById(Long id);
    void updateProduct(MultipartFile[] imageProducts, ProductDTO productDTO);
    void deleteById(Long id);
}
