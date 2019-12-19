package com.savek.RestJpaSpringExampleApp.repository.exception;

/**
 * Неверный ID адреса
 * */
public class AddressIdMismatchException extends RuntimeException {

	public AddressIdMismatchException(Throwable cause) {
		super(cause);
	}

	public AddressIdMismatchException(String message) {
		super(message);
	}

	public AddressIdMismatchException() {
	}

	public AddressIdMismatchException(String message, Throwable cause) {
		super(message, cause);
	}
}
