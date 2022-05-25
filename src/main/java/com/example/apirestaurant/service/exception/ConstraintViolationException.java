package com.example.apirestaurant.service.exception;

public class ConstraintViolationException extends RuntimeException {

	public ConstraintViolationException(String msg) {
		super(msg);
	}
}