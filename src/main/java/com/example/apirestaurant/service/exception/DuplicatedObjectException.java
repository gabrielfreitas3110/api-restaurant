package com.example.apirestaurant.service.exception;

public class DuplicatedObjectException extends RuntimeException {

	public DuplicatedObjectException(String msg) {
		super(msg);
	}
}