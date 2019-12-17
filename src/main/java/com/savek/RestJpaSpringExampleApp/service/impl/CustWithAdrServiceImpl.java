package com.savek.RestJpaSpringExampleApp.service.impl;

import com.savek.RestJpaSpringExampleApp.model.CustWithAdr;
import com.savek.RestJpaSpringExampleApp.repository.CustWithAdrRepository;
import com.savek.RestJpaSpringExampleApp.service.CustWithAdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustWithAdrServiceImpl implements CustWithAdrService {

	CustWithAdrRepository custWithAdrRepository;

	@Autowired
	public CustWithAdrServiceImpl(CustWithAdrRepository custWithAdrRepository) {
		this.custWithAdrRepository = custWithAdrRepository;
	}

	@Override
	public CustWithAdr getByCustomerId(Long id) {
		return custWithAdrRepository.getByCustomerId(id);
	}
}
