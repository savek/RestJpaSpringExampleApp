package com.savek.RestJpaSpringExampleApp;

import com.savek.RestJpaSpringExampleApp.model.Address;
import com.savek.RestJpaSpringExampleApp.model.Customer;
import com.savek.RestJpaSpringExampleApp.model.enums.Sex;
import com.savek.RestJpaSpringExampleApp.repository.AddressRepository;
import com.savek.RestJpaSpringExampleApp.repository.CustomerRepository;
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
	AddressRepository addressRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			fillData();
			showAddressList();
			showCustomerList();

			showCustomersViaAddress(1);
		};
	}

	/** Отображение списка покупателей по номеру адреса */
	private void showCustomersViaAddress(int adr_no) {
		log.info("\n");
		log.info(String.format("Customers found for address %s:", adr_no));
		log.info("-------------------------------");
		addressRepository.findById(adr_no)
				.getCustomers()
				.forEach(customer -> log.info("Customer id = " + customer.getId() + ", " + customer.getFirstName() + " " + customer.getLastName()));
	}

	/** Отображение списка клиентов */
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

	/** Заполнение табличек данными */
	void fillData() {
		Address address_1 = new Address("Россия",
				"Урал",
				"Екатеринбург",
				"Лаптева",
				"1",
				"1");

		Address address_2 = new Address("Россия",
				"Урал",
				"Екатеринбург",
				"Носарева",
				"15",
				"27");

		addressRepository.save(address_1);
		addressRepository.save(address_2);

		Customer customer_1 = new Customer(address_1.getId(), address_2.getId(), "Владимир", "Шахрин", "Олегович", Sex.MALE);
		customerRepository.save(customer_1);

		Customer customer_2 = new Customer(address_2.getId(), address_1.getId(), "Зомбаков", "Валерий", "Генадьевич", Sex.MALE);
		customerRepository.save(customer_2);

		Customer customer_3 = new Customer(address_1.getId(), address_2.getId(), "Владимир", "Шахрин", "Олегович", Sex.MALE);
		customerRepository.save(customer_3);

		Customer customer_4 = new Customer(address_1.getId(), address_1.getId(), "Владимир", "Шахрин", "Олегович", Sex.MALE);
		customerRepository.save(customer_4);

	}
}
