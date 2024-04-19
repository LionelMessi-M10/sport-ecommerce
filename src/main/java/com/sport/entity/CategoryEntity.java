package com.sport.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "active")
	private Integer active;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categoryEntities", cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	private List<ProductEntity> productEntities = new ArrayList<>();
}
