package com.sport.converter;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sport.entity.CouponEntity;
import com.sport.model.dto.ProductDTO;

@Component
public class CouponConverter {

    private final ModelMapper modelMapper;
    private DateConverter dateConverter;

    public CouponConverter(ModelMapper modelMapper, DateConverter dateConverter) {
        this.modelMapper = modelMapper;
        this.dateConverter = dateConverter;
    }

    public CouponEntity convertCouponEntity(ProductDTO productDTO) throws ParseException {
        CouponEntity couponEntity = this.modelMapper.map(productDTO, CouponEntity.class);
        couponEntity.setCouponStartDate(this.dateConverter.convertDate(productDTO.getCouponStartDate()));
        couponEntity.setCouponEndDate(this.dateConverter.convertDate(productDTO.getCouponEndDate()));
        return couponEntity;
    }
}
