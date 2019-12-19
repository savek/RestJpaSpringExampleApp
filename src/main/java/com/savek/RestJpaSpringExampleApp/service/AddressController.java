package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.Address;
import com.savek.RestJpaSpringExampleApp.repository.AddressRepository;
import com.savek.RestJpaSpringExampleApp.repository.exception.*;

import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	enum RemoveResult {
		OK, NO_OK_LINKED_ROWS_FOUND
	};

	@Autowired
	AddressRepository addressRepository;

	@GetMapping
	public Iterable findAll() {
		return addressRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Address create(@RequestBody Address address) {
		address.setDefaultDates();
		return addressRepository.save(address);
	}

	@GetMapping("/{id}")
	public Address findById(@PathVariable long id) {
		return addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
	}

	@PutMapping("/{id}")
	public Address updateAddress(@RequestBody Address updAdr, @PathVariable Long id) {
		if (updAdr.getId() != id) {
			throw new AddressIdMismatchException();
		}

		Address foundAdr = addressRepository.findById(id)
				.orElseThrow(AddressNotFoundException::new);

		foundAdr.setContry(updAdr.getContry());
		foundAdr.setRegion(updAdr.getRegion());
		foundAdr.setCity(updAdr.getCity());
		foundAdr.setStreet(updAdr.getStreet());
		foundAdr.setHouse(updAdr.getHouse());
		foundAdr.setFlat(updAdr.getFlat());
		foundAdr.setModified(Address.getSysDate());

		return addressRepository.save(foundAdr);
	}

	@DeleteMapping("/{id}")
	public Map<String, RemoveResult> deleteAddress(@PathVariable Long id) {
		addressRepository.findById(id)
				.orElseThrow(AddressNotFoundException::new);

		addressRepository.deleteById(id);

		return Collections.singletonMap("RESULT", RemoveResult.OK);
	}
}
