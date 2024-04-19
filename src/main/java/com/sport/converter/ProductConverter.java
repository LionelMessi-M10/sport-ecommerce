package com.sport.converter;

import com.sport.entity.CategoryEntity;
import com.sport.entity.CouponEntity;
import com.sport.entity.ProductEntity;
import com.sport.entity.UserEntity;
import com.sport.entity.VariantEntity;
import com.sport.model.dto.ProductDTO;
import com.sport.service.CategoryService;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final VariantConverter variantConverter;
    private final CouponConverter couponConverter;

    public ProductConverter(ModelMapper modelMapper, CategoryService categoryService, VariantConverter variantConverter, CouponConverter couponConverter){
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.variantConverter = variantConverter;
        this.couponConverter = couponConverter;
    }

    public ProductEntity convertToEntity(ProductDTO productDTO, UserEntity userEntity) throws ParseException{
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);

        

        productEntity.setPublished(1);
        productEntity.setCreatedBy(userEntity.getUserName());

        CategoryEntity categoryEntity = this.categoryService.findByCategoryId(productDTO.getCategoryId());
        productEntity.setCategoryEntities(List.of(categoryEntity));

        VariantEntity variantEntity = this.variantConverter.convertVariantEntity(productDTO);
        variantEntity.setProductEntity(productEntity);
        productEntity.setVariantEntities(List.of(variantEntity));

        CouponEntity couponEntity = this.couponConverter.convertCouponEntity(productDTO);
        couponEntity.setProductEntities(List.of(productEntity));
        productEntity.setCouponEntities(List.of(couponEntity));

        return productEntity;
    }
}
