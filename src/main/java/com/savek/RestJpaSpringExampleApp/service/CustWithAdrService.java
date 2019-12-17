package com.savek.RestJpaSpringExampleApp.service;

import com.savek.RestJpaSpringExampleApp.model.CustWithAdr;

public interface CustWithAdrService {

	CustWithAdr getByCustomerId(Long id);

}
