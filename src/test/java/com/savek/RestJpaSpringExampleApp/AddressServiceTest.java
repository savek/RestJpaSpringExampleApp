package com.savek.RestJpaSpringExampleApp;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RestJpaSpringExampleAppApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressServiceTest {

	private static final String API_ROOT = "http://localhost:%s/api/addresses";

	@LocalServerPort
	private int port;

	/**
	 * URL адрес API для тестирования
	 * */
	private String _apiRoot() {
		return String.format(API_ROOT, port);
	}

	@Test
	public void whenGetAllAddress_thenOK() {

		Response response = RestAssured.get(_apiRoot());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

}
