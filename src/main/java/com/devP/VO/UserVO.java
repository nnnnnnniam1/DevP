package com.devP.VO;

//VO(Value Object)
public class UserVO {
	private String userId;
	private String password;
	private String name;
	private String role;

	private String email;

	public String getUserId() {
		return userId;
	}

	public void setId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() { return email; }
	public void setEmail(String email){ this.email = email; }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}