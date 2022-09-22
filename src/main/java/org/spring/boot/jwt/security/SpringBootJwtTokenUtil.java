package org.spring.boot.jwt.security;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.jwt.model.JwtUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class SpringBootJwtTokenUtil implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootJwtTokenUtil.class);
	
	private static final long serialVersionUID = -8415821498813152754L;

	public static final long JWT_TOKEN_VALIDITY = 18000000;

	@Value("${spring.jwt.secret}")
	private String jwtSecret;

	public String getUsernameFromToken(String token) {
		String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		logger.info("Username : {}", username);
		return username;
	}

	public JwtUserResponse doGenerateToken(String userName) {
		JwtUserResponse response = new JwtUserResponse(Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact());
		logger.info("JwtUserResponse : {}", response);
		return response;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
				| IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		}
	}

}