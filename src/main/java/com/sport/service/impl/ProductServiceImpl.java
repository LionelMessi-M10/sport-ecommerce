package com.sport.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sport.converter.ProductConverter;
import com.sport.entity.ImageProductEntity;
import com.sport.entity.ProductEntity;
import com.sport.entity.UserEntity;
import com.sport.model.dto.ProductDTO;
import com.sport.repository.ProductRepository;
import com.sport.service.ProductService;
import com.sport.service.UploadService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UploadService uploadService;
    private final ProductConverter productConverter;

    public ProductServiceImpl(ProductRepository productRepository, UploadService uploadService, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.uploadService = uploadService;
        this.productConverter = productConverter;
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

    @Override
    @Transactional
    public void save(MultipartFile[] imageProduct, ProductDTO productDTO, UserEntity userEntity) throws ParseException {
        ProductEntity productEntity = this.productConverter.convertToEntity(productDTO, userEntity);

        productEntity.setCreatedAt(new Date());

        List<ImageProductEntity> imageProductEntities = new ArrayList<>();

        for(MultipartFile item : imageProduct){
            String pathImage = this.uploadService.handleSaveUploadFile(item, "images");
            productEntity.setImage(pathImage);

            ImageProductEntity imageProductEntity = new ImageProductEntity();
            imageProductEntity.setProductEntity(productEntity);
            imageProductEntity.setImage(pathImage);

            imageProductEntities.add(imageProductEntity);
        }

        productEntity.setImageProductEntities(imageProductEntities);
        this.productRepository.save(productEntity);
    }

    @Override
    public ProductEntity findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void updateProduct(MultipartFile[] imageProducts, ProductDTO productDTO) {
        List<String> imageList = new ArrayList<>();
        for(MultipartFile file : imageProducts){
            String filePath = this.uploadService.handleSaveUploadFile(file, "product-images");
            imageList.add(filePath);
        }
        
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
