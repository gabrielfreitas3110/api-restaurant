package com.example.apirestaurant.service.exception;

public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}