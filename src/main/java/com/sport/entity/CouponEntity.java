package com.sport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	@CreatedDate
	private Date createdAt;

	@Column(name = "modified_at")
	@LastModifiedDate
	private Date modifiedAt;

	@ManyToMany(mappedBy = "couponEntities", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	private List<ProductEntity> productEntities = new ArrayList<>();

}
