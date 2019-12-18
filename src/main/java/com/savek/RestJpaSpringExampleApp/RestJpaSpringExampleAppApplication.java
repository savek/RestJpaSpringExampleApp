package com.savek.RestJpaSpringExampleApp;

import com.savek.RestJpaSpringExampleApp.model.Address;
import com.savek.RestJpaSpringExampleApp.model.Customer;
import com.savek.RestJpaSpringExampleApp.model.enums.Sex;
import com.savek.RestJpaSpringExampleApp.repository.AddressRepository;
import com.savek.RestJpaSpringExampleApp.repository.CustWithAdrRepository;
import com.savek.RestJpaSpringExampleApp.repository.CustomerRepository;
import com.savek.RestJpaSpringExampleApp.repository.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestJpaSpringExampleAppApplication {

	private static final Logger log = LoggerFactory.getLogger(RestJpaSpringExampleAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestJpaSpringExampleAppApplication.class, args);
	}

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CustWithAdrRepository custWithAdrRepository;

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
//			fillData();
//			showAddressList();
//			showCustomerList();

			showCustomersViaAddress(3);
//			log.info("Customer count: " + customerRepository.getAllCount());
//
//			Address adr_1 = addressRepository.findById(1).get();
//			log.info("Customer linked to registred address: " + addressRepository.getRegistredLinkedCustomerCount(adr_1.getId()));
//			log.info("Customer linked to actual address: " + addressRepository.getActualLinkedCustomerCount(adr_1.getId()));
//
//			log.info(custWithAdrRepository.getByCustomerId(100L).toString());
		};
	}

	/**
	 * Отображение списка покупателей по номеру адреса
	 * */
	private void showCustomersViaAddress(long adr_no) {
		log.info("\n");
		log.info(String.format("Customers found for address %s:", adr_no));
		log.info("-------------------------------");
		addressRepository.findById(adr_no).orElseThrow(AddressNotFoundException::new)
				.getCustomers()
				.forEach(customer -> log.info("Customer id = " + customer.getId() + ", " + customer.getFirstName() + " " + customer.getLastName()));
	}

	/**
	 *  Отображение списка клиентов
	 *  */
	private void showCustomerList() {
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : customerRepository.findAll())
			log.info(customer.toString());
	}

	/** Отображение списка адресов */
	private void showAddressList() {
		log.info("Addresses found with findAll():");
		log.info("-------------------------------");
		for (Address address : addressRepository.findAll())
			log.info(address.toString());
	}

	/**
	 * Заполнение табличек данными
	 * */
	void fillData() {
		Address address_1 = new Address("Россия",
				"Урал",
				"Екатеринбург",
				"Колмогорова",
				"15",
				"9");

		Address address_2 = new Address("Россия",
				"Урал",
				"Екатеринбург",
				"Толмачёва",
				"54",
				"12");

		addressRepository.save(address_1);
		addressRepository.save(address_2);

		Customer customer_1 = new Customer("Шахрин", "Владимир", "Олегович", address_1, address_2, Sex.MALE);
		customerRepository.save(customer_1);

		Customer customer_2 = new Customer("Зомбаков", "Валерий", "Генадьевич", address_2, address_1, Sex.MALE);
		customerRepository.save(customer_2);

		Customer customer_3 = new Customer("Кудря", "Николай", "Петрович", address_1, address_2, Sex.MALE);
		customerRepository.save(customer_3);

		Customer customer_4 = new Customer("Осипов", "Юрий", "Алексеевич", address_1, address_1, Sex.MALE);
		customerRepository.save(customer_4);

	}
}
