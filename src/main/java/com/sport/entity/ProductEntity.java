package com.sport.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image")
	private String image;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "regular_price")
	private Double regularPrice;

	@Column(name = "discount_price")
	private Double discountPrice;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "product_note")
	private String productNote;

	@Column(name = "published")
	private Integer published;

	@Column(name = "created_at")
	@CreatedDate
	private Date createdAt;

	@Column(name = "modified_at")
	@LastModifiedDate
	private Date modifiedAt;

	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<VariantEntity> variantEntities = new ArrayList<>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<OrderItemEntity> orderItemEntities = new ArrayList<>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<ProductShippingEntity> productShippingEntities = new ArrayList<>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<CartItemEntity> cartItemEntities = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "product_coupons", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	private List<CouponEntity> couponEntities = new ArrayList<>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<ImageProductEntity> imageProductEntities = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CategoryEntity> categoryEntities = new ArrayList<>();
}
