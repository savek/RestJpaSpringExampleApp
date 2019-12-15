package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.Address;
import java.util.List;

public interface AddressRepositoryService {

    List<Address> findAll();

    void save(Address address);

    Address findById(long adr_no);

    /**
     *  Возврат кол-ва покупателей связанных с адресом {@link Address} registred
     * @param adr Объект адреса @{@link Address}
     * */
    long getRegistredLinkedCustomerCount(Address adr);

    /**
     *  Возврат кол-ва покупателей связанных по адресу @{@link Address} actual
     *  @param adr Объект адреса @{@link Address}
     *  */
    long getActualLinkedCustomerCount(Address adr);
}
