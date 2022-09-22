package org.spring.boot.jwt.model;

import java.io.Serializable;

public class JwtUserResponse implements Serializable {

	private static final long serialVersionUID = -1848018703973175298L;

	private final String jwtUserToken;

	public JwtUserResponse(String jwtUserToken) {
		this.jwtUserToken = jwtUserToken;
	}
	
	public String getJwtUserToken() {
		return jwtUserToken;
	}

	@Override
	public String toString() {
		return "JwtUserResponse [jwtUserToken=" + jwtUserToken + "]";
	}

}