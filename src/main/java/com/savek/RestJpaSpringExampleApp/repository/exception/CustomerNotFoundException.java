package com.savek.RestJpaSpringExampleApp.repository.exception;

/**
 * Покупатель с таким ID не найден
 * */
public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String message) {
		super(message);
	}

	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
