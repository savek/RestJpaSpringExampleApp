package com.savek.RestJpaSpringExampleApp.repository;

import com.savek.RestJpaSpringExampleApp.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
	Address findById(long id);
}
