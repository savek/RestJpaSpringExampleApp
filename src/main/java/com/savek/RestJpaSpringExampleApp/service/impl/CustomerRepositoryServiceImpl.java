package com.savek.RestJpaSpringExampleApp.service.impl;

import com.savek.RestJpaSpringExampleApp.model.Customer;
import com.savek.RestJpaSpringExampleApp.repository.CustomerRepository;
import com.savek.RestJpaSpringExampleApp.service.CustomerRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerRepositoryServiceImpl implements CustomerRepositoryService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    /** Возврат общего кол-ва покупаталей */
    @Override
    public long getAllCount() {
        return customerRepository.getAllCount();
    }
}
