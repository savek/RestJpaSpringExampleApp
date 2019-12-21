package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.Customer;
import com.savek.RestJpaSpringExampleApp.repository.CustomerRepository;
import com.savek.RestJpaSpringExampleApp.repository.exception.CustomerIdMismatchException;
import com.savek.RestJpaSpringExampleApp.repository.exception.CustomerNotFoundException;
import com.savek.RestJpaSpringExampleApp.service.enums.RemoveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerContoller {

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping
	public Iterable findAll() {
		return customerRepository.findAll();
	}

	@GetMapping("/{customerId}")
	public Customer findById(@PathVariable long customerId) {
		return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer create(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PutMapping("/{id}")
	public Customer updateAddress(@RequestBody Customer updCust, @PathVariable Long id) {
		if (!updCust.getId().equals(id)) {
			throw new CustomerIdMismatchException();
		}

		Customer foundCust = customerRepository
				.findById(id)
				.orElseThrow(CustomerNotFoundException::new);

		foundCust.setLastName(updCust.getLastName());
		foundCust.setFirstName(updCust.getFirstName());
		foundCust.setMiddleName(updCust.getMiddleName());
		foundCust.setSex(updCust.getSex());
		foundCust.setRegistredAddresId(updCust.getRegistredAddresId());
		foundCust.setActualAddressId(updCust.getActualAddressId());

		return customerRepository.save(foundCust);
	}

	@DeleteMapping("/{id}")
	public Map<String, RemoveResult> deleteAddress(@PathVariable Long id) {
		customerRepository
				.findById(id)
				.orElseThrow(CustomerNotFoundException::new);

		customerRepository.deleteById(id);
		return Collections.singletonMap("RESULT", RemoveResult.OK);
	}

	@GetMapping("/findByFirstAndLastName")
	public List<Customer> findByFirstAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
		return customerRepository.findByFirstNameAndLastName(firstName, lastName);
	}
}

