package com.savek.RestJpaSpringExampleApp.repository.exception;

/**
 * Неверный ID покупателя
 * */
public class CustomerIdMismatchException extends RuntimeException {
	public CustomerIdMismatchException() {
	}

	public CustomerIdMismatchException(String message) {
		super(message);
	}

	public CustomerIdMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerIdMismatchException(Throwable cause) {
		super(cause);
	}
}
