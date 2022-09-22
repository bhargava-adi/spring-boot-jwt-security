package org.spring.boot.jwt.controller;

import org.spring.boot.jwt.model.JwtUserRequest;
import org.spring.boot.jwt.model.JwtUserResponse;
import org.spring.boot.jwt.security.SpringBootJwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootJwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private SpringBootJwtTokenUtil jwtTokenUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<JwtUserResponse> createAuthenticationToken(@RequestBody JwtUserRequest request) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
			authenticationManager.authenticate(token);
			return ResponseEntity.ok(jwtTokenUtil.doGenerateToken(request.getUsername()));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}

	@GetMapping("/message")
	public String getMessage() {
		return "Hi, Welcome to JWT Security World.";
	}

}