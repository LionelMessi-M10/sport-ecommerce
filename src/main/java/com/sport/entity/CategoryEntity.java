package com.sport.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Column(name = "image", columnDefinition = "BYTEA")
	private String image;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "active")
	private Integer active;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categoryEntities")
	private List<ProductEntity> productEntities = new ArrayList<>();
}
