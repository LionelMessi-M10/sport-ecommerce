package com.sport.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coupons")
public class CouponEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "code", length = 255)
	private String code;
	
	@Column(name = "coupon_description", length = 255)
	private String couponDescription;
	
	@Column(name = "discount_value")
	private Double discountValue;
	
	@Column(name = "discount_type")
	private String discountType;
	
	@Column(name = "time_used")
	private Integer timeUsed;
	
	@Column(name = "max_usage")
	private Integer maxUsage;
	
	@Column(name = "coupon_start_date")
	private Date couponStartDate;
	
	@Column(name = "coupon_end_date")
	private Date couponEndDate;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "modified_by")
	private Integer modifiedBy;
	
	@OneToMany(mappedBy = "couponEntity", fetch = FetchType.LAZY)
	private List<ProductCouponEntity> productCouponEntities = new ArrayList<>();
}
