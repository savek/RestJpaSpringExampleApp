package com.savek.RestJpaSpringExampleApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Address {
	@Id
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
}
