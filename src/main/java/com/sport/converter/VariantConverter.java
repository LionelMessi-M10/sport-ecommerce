package com.sport.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sport.entity.VariantEntity;
import com.sport.model.dto.ProductDTO;

@Component
public class VariantConverter {

  public List<VariantEntity> convertVariantEntity(ProductDTO productDTO){
    List<VariantEntity> variantEntities = new ArrayList<>();

    for(String size : productDTO.getSize()){
      VariantEntity variantEntity = new VariantEntity();
      variantEntity.setSize(size);
      variantEntities.add(variantEntity);
    }

    for(String color : productDTO.getColorPro()){
      for(VariantEntity item : variantEntities){
        item.setColorPro(color);
      }
    }

    return variantEntities;
  }
}
