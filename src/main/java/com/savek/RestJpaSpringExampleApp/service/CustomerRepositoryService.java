package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.Customer;
import java.util.List;

public interface CustomerRepositoryService {
    List<Customer> findAll();
}
