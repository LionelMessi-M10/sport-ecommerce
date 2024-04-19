package com.sport.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

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
import jakarta.persistence.OneToOne;
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
@Table(name = "orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "total")
	private Double total;

	@Column(name = "status")
	private Integer status; // trang thai giao hang

	@Column(name = "created_at")
	@CreatedDate
	private Date created_at; // ngay khach hang tao don hang (dat hang)

	@Column(name = "order_approved_at")
	private Date orderApprovedAt; // ngay duyet don

	@Column(name = "order_delivered_carrier_date")
	private Date orderDeliveredCarrierDate; // ngay van chuyen don hang

	@Column(name = "order_delivered_customer_date")
	private Date orderDeliveredCustomerDate; // ngay ban giao hang cho khach hang

	@OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<OrderItemEntity> orderDetailEntities = new ArrayList<>();

	@OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<OrderStatusEntity> orderStatusEntities = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private UserEntity userEntity;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "coupon_id", referencedColumnName = "id")
	private CouponEntity couponEntity;
}
