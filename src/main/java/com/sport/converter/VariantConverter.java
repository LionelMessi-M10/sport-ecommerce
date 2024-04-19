package com.sport.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.sport.entity.VariantEntity;
import com.sport.model.dto.ProductDTO;

@Component
public class VariantConverter {

  private final ModelMapper modelMapper;

  public VariantConverter(ModelMapper modelMapper){
    this.modelMapper = modelMapper;
  }

  public VariantEntity convertVariantEntity(ProductDTO productDTO){
    VariantEntity variantEntity = modelMapper.map(productDTO, VariantEntity.class);
    return variantEntity;
  }
}
