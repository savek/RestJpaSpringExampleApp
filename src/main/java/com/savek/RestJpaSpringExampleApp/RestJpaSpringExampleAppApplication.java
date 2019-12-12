package com.savek.RestJpaSpringExampleApp;

import com.savek.RestJpaSpringExampleApp.model.Address;
import com.savek.RestJpaSpringExampleApp.model.Customer;
import com.savek.RestJpaSpringExampleApp.model.enums.Sex;
import com.savek.RestJpaSpringExampleApp.repository.AddressRepository;
import com.savek.RestJpaSpringExampleApp.repository.CustomerRepository;
import lombok.AllArgsConstructor;
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

//			Address address_1 = new Address("Россия",
//											"Урал",
//											"Екатеринбург",
//											"Лаптева",
//											"1",
//											"1");
//
//			Address address_2 = new Address("Россия",
//											"Урал",
//											"Екатеринбург",
//											"Носарева",
//											"15",
//											"27");
//
//			addressRepository.save(address_1);
//			addressRepository.save(address_2);

//			addressRepository.findAll();

			log.info("Addresses found with findAll():");
			log.info("-------------------------------");
			for (Address address : addressRepository.findAll())
				log.info(address.toString());

			//			repository.save(new Customer());

//			Address address_1 = addressRepository.findById(1);
//			Address address_2 = addressRepository.findById(2);
//
//			Customer customer_1 = new Customer(address_1.getId(), address_2.getId(), "Владимир", "Шахрин", "Олегович", Sex.MALE);
//			customerRepository.save(customer_1);

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : customerRepository.findAll())
				log.info(customer.toString());


//			// save a few customers
//			repository.save(new Customer("Jack", "Bauer"));
//			repository.save(new Customer("Chloe", "O'Brian"));
//			repository.save(new Customer("Kim", "Bauer"));
//			repository.save(new Customer("David", "Palmer"));
//			repository.save(new Customer("Michelle", "Dessler"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			Customer customer = repository.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			// for (Customer bauer : repository.findByLastName("Bauer")) {
//			//  log.info(bauer.toString());
//			// }
//			log.info("");
		};
	}
}
