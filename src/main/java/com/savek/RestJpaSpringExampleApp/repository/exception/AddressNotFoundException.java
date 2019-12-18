package com.savek.RestJpaSpringExampleApp.repository.exception;

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
