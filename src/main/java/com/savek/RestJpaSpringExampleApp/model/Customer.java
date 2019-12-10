package com.savek.RestJpaSpringExampleApp.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(name = "registred_address_id", nullable = false)
	private Long registredAddresId;

	@Column(name = "actual_address_id", nullable = false)
	private Long actualAddressId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@Column(name = "sex", nullable = false, length = 6)
	private String sex;
}
