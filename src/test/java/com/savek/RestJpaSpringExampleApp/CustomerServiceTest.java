package com.savek.RestJpaSpringExampleApp;

import com.savek.RestJpaSpringExampleApp.model.Address;
import com.savek.RestJpaSpringExampleApp.model.Customer;
import com.savek.RestJpaSpringExampleApp.model.enums.Sex;
import com.savek.RestJpaSpringExampleApp.repository.AddressRepository;
import com.savek.RestJpaSpringExampleApp.repository.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = RestJpaSpringExampleAppApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {
	private static final String API_ROOT = "http://localhost:%s/api/customers";

	@LocalServerPort
	private int port;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	/**
	 * URL адрес API для тестирования
	 * */
	private String _apiRoot() {
		return String.format(API_ROOT, port);
	}

	/**
	 * Получение списка клиентов
	 * */
	@Test
	public void whenGetAllCustomers_thenOK() {
		Response response = RestAssured.get(_apiRoot());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	/**
	 * Проверка выборки адреса по ID
	 * */
	@Test
	public void whenGetCustomerById_thenOK() {
		// Создание покупателя
		Customer customer = createCustomerViaRepository();

		Response response = RestAssured.get(_apiRoot() + "/" + customer.getId());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals(customer.getId().toString(), response.jsonPath().get("id").toString());
	}

	/**
	 * Проверка случая, когда покупатель не найден по ID
	 * */
	@Test
	public void whenGetNotExistAddressById_thenNotFound() {
		Response response = RestAssured.get(_apiRoot() + "/" + "-1");
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

	/** Удаление адреса по ID */
	@Test
	public void RemoveCustomerById() {
		// Создание покупателя
		Customer customer = createCustomerViaRepository();

		Response response = RestAssured.delete(_apiRoot() + "/" + customer.getId());
		// Удаление прошло успешно
		assertEquals(response.jsonPath().get("RESULT"), "OK");
	}

	/** Создание покупателя */
	@Test
	public void whenCreateCustomer_thenOk() {
		// Регистрация адресов
		Address adrActual = createActualAddress();
		Address adrRegistred = createRegistredAddress();

		// Формирование JSON-запроса
		String customerJson = "{" +
				"    \"actualAddressId\": "+ adrActual.getId() +"," +
				"    \"firstName\": \"Валерий\"," +
				"    \"lastName\": \"Зомбаков\"," +
				"    \"middleName\": \"Генадьевич\"," +
				"    \"registredAddresId\": " + adrRegistred.getId() + "," +
				"    \"sex\": \"MALE\"" +
				"  }";

		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(customerJson)
				.post(_apiRoot());

		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}

	/**
	 * Поиск покупателя по имени и фамилии
	 * */
	@Test
	public void whenFindByFirstAndLastName_thenOk() {
		// Создание покупателя
		Customer customer = createCustomerViaRepository();

		Response response = RestAssured.get(_apiRoot()
				+ "/findByFirstAndLastName/"
				+ "?firstName="
				+ customer.getFirstName()
				+ "&lastName="
				+ customer.getLastName());

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertTrue(response.as(List.class).size() > 0);
	}

	/**
	 * Создание объекта Customer через слой Repository
	 * */
	private Customer createCustomerViaRepository() {
		// Регистрация адресов
		Address adrActual = createActualAddress();
		Address adrRegistred = createRegistredAddress();

		// Регистрация покупателя
		Customer customer = new Customer("Холмс", "Шерлок", "-", adrActual, adrRegistred, Sex.MALE);
		customerRepository.save(customer);
		return customer;
	}

	private Address createRegistredAddress() {
		Address adrRegistred = new Address("Англия", "Большой Лондон", "Лондон", "Бэйкер стрит", "239", "-");
		addressRepository.save(adrRegistred);
		return adrRegistred;
	}

	private Address createActualAddress() {
		Address adrActual = new Address("Англия", "Большой Лондон", "Лондон", "Бэйкер стрит", "221Б", "NW1 6XE");
		addressRepository.save(adrActual);
		return adrActual;
	}
}
