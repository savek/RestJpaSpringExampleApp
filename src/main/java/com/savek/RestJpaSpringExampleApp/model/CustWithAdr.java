package com.savek.RestJpaSpringExampleApp.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

/** Сущность "Покупатель с адресом" для выборки через jdbc */
@AllArgsConstructor
@ToString
public class CustWithAdr {

	private Long customerId;

	private String firstName;

	private String lastName;

	private String middleName;

	private String registredAddress;

	private String actualAddress;

}
