package org.spring.boot.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SpringBootJwtControllerAdvice {

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<String> userDisabledException(DisabledException e) {
		return new ResponseEntity<>("User is disabled", HttpStatus.LOCKED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> badCredentialsException(BadCredentialsException e) {
		return new ResponseEntity<>("Bad Credentials Found for User", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> usernameNotFoundException(UsernameNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}
}