package com.savek.RestJpaSpringExampleApp.repository;

import com.savek.RestJpaSpringExampleApp.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
	Address findById(long id);

	/**
	 *  Возврат общего кол-ва адресов
	 *  */
	@Query(value = "select count(1) from addresses", nativeQuery = true)
	long getAllCount();

	/**
	 * Возврат кол-ва покупателей связанных по адресу registred
	 * */
	@Query(value = "select count(1) " +
			" from customer " +
			" where registred_address_id = :ADR_ID ",
			nativeQuery = true)
	long getRegistredLinkedCustomerCount(@Param("ADR_ID") long adrId);

	/**
	 * Возврат кол-ва покупателей связанных по адресу actual
	 * */
	@Query(value = "select count(1) " +
			" from customer " +
			" where actual_address_id = :ADR_ID ",
			nativeQuery = true)
	long getActualLinkedCustomerCount(@Param("ADR_ID") long adrId);
}
