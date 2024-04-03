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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "status")
	private Integer status; // trang thai giao hang
	
	@Column(name = "created_at")
	private Date created_at; // ngay khach hang tao don hang (dat hang)
	
	@Column(name = "order_approved_at")
	private Date orderApprovedAt; // ngay duyet don
	
	@Column(name = "order_delivered_carrier_date")
	private Date orderDeliveredCarrierDate; // ngay van chuyen don hang
	
	@Column(name = "order_delivered_customer_date")
	private Date orderDeliveredCustomerDate; // ngay ban giao hang cho khach hang
	
	@OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY)
	private List<OrderItemEntity> orderDetailEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY)
	private List<OrderStatusEntity> orderStatusEntities = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private UserEntity userEntity;
}
