package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.RestJpaSpringExampleAppApplication;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
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

	/**
	 * Получение списка адресов
	 * */
	@Test
	public void whenGetAllAddress_thenOK() {
		Response response = RestAssured.get(_apiRoot());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	/**
	 * Проверка выборки адреса по ID
	 * */
	@Test
	public void whenGetAddressById_thenOK() {
		String createdAdrId = createRandomAddress();

		Response response = RestAssured.get(_apiRoot() + "/" + createdAdrId);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals(createdAdrId, response.jsonPath().get("id").toString());
	}

	/**
	 * Проверка случая, когда адресс не найден по ID
	 * */
	@Test
	public void whenGetNotExistAddressById_thenNotFound() {
		Response response = RestAssured.get(_apiRoot() + "/" + "-1");

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

	/** Удаление адреса по ID */
	@Test
	public void RemoveAddressById() {
		String createdAdrId = createRandomAddress();
		Response response = RestAssured.delete(_apiRoot() + "/" + createdAdrId);

		// Удаление прошло успешно
		assertEquals(response.jsonPath().get("RESULT"), "OK");
	}

	/**
	 * Проверка выборки адреса по ID.
	 * Сперва случайный адрес формируется, затем обновляется.
	 * */
	@Test
	public void whenUpdateAddressById_thenOK() {
		String createdAdrId = createRandomAddress();
		String modifyedJsonBody = getRandomAdrJsonId(createdAdrId);

		Response response = RestAssured
				.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(modifyedJsonBody)
				.put(_apiRoot() + "/" + createdAdrId);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	/**
	 * Формирование случайного адреса в формате JSON
	 * */
	private String getRandomAdrJson() {
		// Формат объекта Address
		String address = "{\"contry\":\"%s\",\"region\":\"%s\",\"city\":\"%s\",\"street\":\"%s\",\"house\":\"%s\",\"flat\":\"%s\"}";

		return String.format(address,
				randomAlphabetic(10), // country
				randomAlphabetic(15), // region
				randomAlphabetic(8),  // city
				randomAlphabetic(20), // street
				randomNumeric(2),     // house
				randomNumeric(2));
	}

	/**
	 * Формирование случайного адреса в формате JSON, с заданным ID
	 * */
	private String getRandomAdrJsonId(String id) {
		// Формат объекта Address
		String address = "{\"id\":\"%s\", \"contry\":\"%s\",\"region\":\"%s\",\"city\":\"%s\",\"street\":\"%s\",\"house\":\"%s\",\"flat\":\"%s\"}";

		return String.format(address,
				id,                          // id
				randomAlphabetic(10), // country
				randomAlphabetic(15), // region
				randomAlphabetic(8),  // city
				randomAlphabetic(20), // street
				randomNumeric(2),     // house
				randomNumeric(2));
	}

	/**
	 * Формирование случайного адреса
	 * @return ID сформированного адреса
	 * */
	private String createRandomAddress() {
		// Тело "Адреса" в формате JSON
		String randomAdrJson = getRandomAdrJson();

		// Запрос на создание адреса
		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(randomAdrJson)
				.post(_apiRoot());

		return response.jsonPath().get("id").toString();
	}
}
