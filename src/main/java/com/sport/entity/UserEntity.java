package com.sport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	@CreatedDate
	private Date createdAt;

	@Column(name = "modified_at")
	@LastModifiedDate
	private Date modifiedAt;

	@Column(name = "enabled")
	private Integer enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roleEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	List<OrderEntity> orderEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<CartEntity> cartEntities = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = true)
	private List<CustomerAddressEntity> customerAddressEntities = new ArrayList<>();
}
