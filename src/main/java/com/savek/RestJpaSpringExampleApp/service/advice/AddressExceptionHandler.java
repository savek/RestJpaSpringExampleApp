package com.savek.RestJpaSpringExampleApp.service.advice;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.savek.RestJpaSpringExampleApp.repository.exception.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AddressExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ AddressNotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex,
				"Address not found",
				new HttpHeaders(),
				HttpStatus.NOT_FOUND,
				request);
	}

	@ExceptionHandler({ AddressIdMismatchException.class,
						ConstraintViolationException.class,
						DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex,
				"Invalid data sent in request",
				new HttpHeaders(),
				HttpStatus.BAD_REQUEST,
				request);
	}

}
