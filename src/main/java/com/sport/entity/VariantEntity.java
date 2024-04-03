package com.sport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "variants")
public class VariantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "variant_name", length = 255)
	private String variantName;
	
	@Column(name = "variant_value", length = 255)
	private String variantValue;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;
}
