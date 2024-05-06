package com.sport.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sport.entity.CategoryEntity;
import com.sport.entity.CouponEntity;
import com.sport.entity.ProductEntity;
import com.sport.entity.UserEntity;
import com.sport.entity.VariantEntity;
import com.sport.model.dto.ProductDTO;
import com.sport.service.CategoryService;

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

        List<VariantEntity> variantEntities = this.variantConverter.convertVariantEntity(productDTO);
        for(VariantEntity variantEntity : variantEntities){
            variantEntity.setProductEntity(productEntity);
        }
        productEntity.setVariantEntities(variantEntities);

        CouponEntity couponEntity = this.couponConverter.convertCouponEntity(productDTO);
        couponEntity.setProductEntities(List.of(productEntity));
        productEntity.setCouponEntities(List.of(couponEntity));

        return productEntity;
    }

    public ProductDTO convertToDTO(ProductEntity productEntity, UserEntity userEntity){
        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);


        CouponEntity couponEntity = productEntity.getCouponEntities().get(0);
        productDTO.setCode(couponEntity.getCode());
        productDTO.setCouponDescription(couponEntity.getCouponDescription());
        productDTO.setDiscountValue(couponEntity.getDiscountValue());
        productDTO.setDiscountType(couponEntity.getDiscountType());
        productDTO.setTimeUsed(couponEntity.getTimeUsed());
        productDTO.setMaxUsage(couponEntity.getMaxUsage());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        productDTO.setCouponStartDate(simpleDateFormat.format(couponEntity.getCouponStartDate()));
        productDTO.setCouponEndDate(simpleDateFormat.format(couponEntity.getCouponEndDate()));

        List<String> sizeProduct = new ArrayList<>();
        List<String> colorProduct = new ArrayList<>();
        for(VariantEntity it : productEntity.getVariantEntities()){
            sizeProduct.add(it.getSize());
            colorProduct.add(it.getColorPro());
        }
        productDTO.setSize(sizeProduct);
        productDTO.setColorPro(colorProduct);

        return productDTO;
    }
}
