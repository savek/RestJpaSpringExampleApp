package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.Address;

import java.util.List;

public interface AddressRepositoryService {
    List<Address> findAll();
    void save(Address _address);
}
