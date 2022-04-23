package com.example.apirestaurant.controller.exception;

import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DuplicatedObjectException.class)
	public ResponseEntity<StandardError> duplicatedObjectException(DuplicatedObjectException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
}