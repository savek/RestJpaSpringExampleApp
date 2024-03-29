package com.savek.RestJpaSpringExampleApp.repository;

import com.savek.RestJpaSpringExampleApp.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);

	Optional<Customer> findById(long id);

	/**
	 * Возврат общего кол-ва покупаталей
	 */
	@Query(value = "select count(1) from customer", nativeQuery = true)
	long getAllCount();


	List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
