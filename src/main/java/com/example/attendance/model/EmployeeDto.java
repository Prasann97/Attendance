package com.example.attendance.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class EmployeeDto {

	private Long id;

	@NotEmpty(message = "Please enter valid name.")
	private String name;

	@NotEmpty(message = "Please enter valid email.")
	@Email
	private String email;

	@NotEmpty(message = "Please enter valid password.")
	private String password;

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

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
