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
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
    @Column(name = "image", columnDefinition = "BYTEA")
    private String image;
	
	@Column(name = "username", length = 255)
	private String userName;
	
	@Column(name = "firstname", length = 255)
	private String firstName;
	
	@Column(name = "lastname", length = 255)
	private String lastName;
	
	@Column(name = "email", length = 255)
	private String email;
	
	@Column(name = "password", length = 255)
	private String password;
	
	@Column(name = "address", length = 255)
	private String address;
	
	@Column(name = "telephone", length = 255)
	private String telephone;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	@Column(name = "status")
	private Integer status;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roleEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "userEntity")
	List<OrderEntity> orderEntities = new ArrayList<>();
	
	@OneToMany(mappedBy = "userEntity")
	private List<CartEntity> cartEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity")
	private List<CustomerAddressEntity> customerAddressEntities = new ArrayList<>();
}
