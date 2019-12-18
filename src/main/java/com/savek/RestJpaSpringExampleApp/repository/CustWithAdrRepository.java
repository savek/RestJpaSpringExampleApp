package com.savek.RestJpaSpringExampleApp.repository;

import com.savek.RestJpaSpringExampleApp.model.CustWithAdr;
import org.springframework.stereotype.Repository;

public interface CustWithAdrRepository {

	/** Возврат данных по ID покупателя
	 * @param id ID покутеля {@link com.savek.RestJpaSpringExampleApp.model.Customer} */
	CustWithAdr getByCustomerId(Long id);

}
