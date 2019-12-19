package com.savek.RestJpaSpringExampleApp.repository.exception;

/**
 * Адрес с таким ID не найден
 * */
public class AddressNotFoundException extends RuntimeException {

	public AddressNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressNotFoundException() {
		super();
	}

	public AddressNotFoundException(Throwable cause) {
		super(cause);
	}

	public AddressNotFoundException(String message) {
		super(message);
	}
}
