package com.example.apirestaurant.service.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String msg) {
		super(msg);
	}
}