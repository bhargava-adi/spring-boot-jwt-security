package org.spring.boot.jwt.model;

import java.io.Serializable;

public class JwtUserRequest implements Serializable {

	private static final long serialVersionUID = 7471578878904455463L;

	private String username;
	
	private String password;

	public JwtUserRequest() {

	}

	public JwtUserRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}