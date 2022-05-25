package com.example.apirestaurant.model.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String msg) {
		super(msg);
	}
}