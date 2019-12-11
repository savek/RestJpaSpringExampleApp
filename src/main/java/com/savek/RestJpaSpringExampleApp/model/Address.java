package com.savek.RestJpaSpringExampleApp.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Address {
	@Id
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String contry;

	@Column(nullable = false)
	private String region;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String house;

	@Column(nullable = false)
	private String flat;

	@Column(nullable = false)
	private Timestamp created;

	@Column(nullable = false)
	private Timestamp modified;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "registred_address_id", referencedColumnName = "id"),
			@JoinColumn(name = "actual_address_id", referencedColumnName = "id")
	})
	private List<Customer> customers;
}
