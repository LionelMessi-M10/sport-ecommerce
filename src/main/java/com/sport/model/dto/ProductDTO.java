package com.sport.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    // Product Entity
    private Long id;
    private MultipartFile[] images;
    private String productName;
    private Double regularPrice;
    private Double discountPrice;
    private Long quantity;
    private String shortDescription;
    private String productDescription;
    private String productNote;
    private Integer published;
    private String createdBy;
    private String modifiedBy;
    private Long categoryId;

    // Coupon Entity
    private String code;
    private String couponDescription;
    private Double discountValue;
    private String discountType;
    private Integer timeUsed;
    private Integer maxUsage;
    private String couponStartDate;
    private String couponEndDate;

    // Variant Entity
    private List<String> size;
    private List<String> colorPro;

}
