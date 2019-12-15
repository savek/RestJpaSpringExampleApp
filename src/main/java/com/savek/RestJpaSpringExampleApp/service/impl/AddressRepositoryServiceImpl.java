package com.savek.RestJpaSpringExampleApp.service.impl;

import com.savek.RestJpaSpringExampleApp.model.Address;
import com.savek.RestJpaSpringExampleApp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.savek.RestJpaSpringExampleApp.service.AddressRepositoryService;
import java.util.List;

@Service
public class AddressRepositoryServiceImpl implements AddressRepositoryService {

    AddressRepository addressRepository;

    @Autowired
    public AddressRepositoryServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address findById(long adr_no) {
        return addressRepository.findById(adr_no);
    }

    /** Возврат кол-ва покупателей связанных с адресом {@link Address} registred
     * @param adr Объект адреса @{@link Address} */
    @Override
    public long getRegistredLinkedCustomerCount(Address adr) {
        return addressRepository.getRegistredLinkedCustomerCount(adr.getId());
    }

    /** Возврат кол-ва покупателей связанных по адресу @{@link Address} actual
     *  @param adr Объект адреса @{@link Address} */
    @Override
    public long getActualLinkedCustomerCount(Address adr) {
        return addressRepository.getActualLinkedCustomerCount(adr.getId());
    }
}
