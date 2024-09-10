package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	private String mobile;

	private String password;

	@Transient
	private String address1[];

	@Transient
	private String address2[];

	@Transient
	private String city[];

	@Transient
	private String pincode[];

	@Transient
	private String state[];

	@Transient
	private Long address_id[];

	public User() {

	}

	public Long[] getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long[] address_id) {
		this.address_id = address_id;
	}

	public String[] getAddress1() {
		return address1;
	}

	public void setAddress1(String[] address1) {
		this.address1 = address1;
	}

	public String[] getAddress2() {
		return address2;
	}

	public void setAddress2(String[] address2) {
		this.address2 = address2;
	}

	public String[] getCity() {
		return city;
	}

	public void setCity(String[] city) {
		this.city = city;
	}

	public String[] getPincode() {
		return pincode;
	}

	public void setPincode(String[] pincode) {
		this.pincode = pincode;
	}

	public String[] getState() {
		return state;
	}

	public void setState(String[] state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public User(Long id, String name, String email, String password, String[] address1, String[] address2,
			String[] city, String[] pincode, String[] state, List<Role> roles, List<Address> address, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.roles = roles;
		this.mobile = mobile;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private List<Role> roles = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_address", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID") })
	private List<Address> address = new ArrayList<>();
}
