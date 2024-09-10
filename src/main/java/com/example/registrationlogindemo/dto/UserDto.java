package com.example.registrationlogindemo.dto;

import java.util.List;

import com.example.registrationlogindemo.entity.Address;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    
    @NotEmpty(message="address Line 1 should not be empty")
    private String address1[];
    
    @NotEmpty(message="address Line 2 should not be empty")
    private String address2[];
    
    @NotEmpty(message="city should not be empty")
    private String city[];
    
    @NotEmpty(message="Pincode should not be empty")
    private String pincode[];
    
    @NotEmpty(message="state should not be empty")
    private String state[];
    
    private String password;
    
    private String mobile;
    
    
	private Long address_id[];
    
    private List<Address> address;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Long[] getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Long[] address_id) {
		this.address_id = address_id;
	}
	
}
