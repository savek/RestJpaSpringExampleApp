package com.savek.RestJpaSpringExampleApp.repository;

import com.savek.RestJpaSpringExampleApp.model.Customer;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);

	Customer findById(long id);
}