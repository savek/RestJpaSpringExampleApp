package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.Address;
import com.savek.RestJpaSpringExampleApp.repository.AddressRepository;
import com.savek.RestJpaSpringExampleApp.repository.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressRepository addressRepository;

	@GetMapping
	public Iterable findAll() {
		return addressRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Address create(@RequestBody Address adr) {
		adr.setAddressFefaultDate();
		return addressRepository.save(adr);
	}

	@GetMapping("/{addressId}")
	public Address findById(@PathVariable long addressId) {
		return addressRepository.findById(addressId).orElseThrow(AddressNotFoundException::new);
	}
}
