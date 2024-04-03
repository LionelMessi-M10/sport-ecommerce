package com.sport.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
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
	private Date createdAt;
	
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "modified_by")
	private Integer modifiedBy;
	
	@OneToMany(mappedBy = "productEntity")
	private List<VariantEntity> variantEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<OrderItemEntity> orderItemEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<SellEntity> sellEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<ProductShippingEntity> productShippingEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<CartItemEntity> cartItemEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<ProductCouponEntity> productCouponEntities = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_categories",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CategoryEntity> categoryEntities = new ArrayList<>();
}
