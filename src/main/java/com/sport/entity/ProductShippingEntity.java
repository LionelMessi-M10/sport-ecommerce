package com.sport.entity;

import java.util.Date;

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
@Table(name = "product_shippings")
public class ProductShippingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ship_change")
	private Double shipChange; // gia ship
	
	@Column(name = "free")
	private Integer free;
	
	@Column(name = "estimated_day") // ngay du kien giao hang
	private Date estimatedDay;
	
	@ManyToOne
	@JoinColumn(name = "shipping_id")
	private ShippingEntity shippingEntity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;

}
