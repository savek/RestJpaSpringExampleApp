package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.Customer;
import com.savek.RestJpaSpringExampleApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.savek.RestJpaSpringExampleApp.repository.exception.*;
import org.springframework.web.bind.annotation.RestController;

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
}
