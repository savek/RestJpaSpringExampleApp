package com.savek.RestJpaSpringExampleApp.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable {

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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "actual_address_id", referencedColumnName = "id")
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private Set<Customer> customers_actual_adr;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "registred_address_id", referencedColumnName = "id")
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private Set<Customer> customers_registred_adr;

	public Address(String contry,
	               String region,
	               String city,
	               String street,
	               String house,
	               String flat) {
		this.contry = contry;
		this.region = region;
		this.city = city;
		this.street = street;
		this.house = house;
		this.flat = flat;
		this.created = new Timestamp(System.currentTimeMillis());
		this.modified = new Timestamp(System.currentTimeMillis());
	}

	/** Возврат списка покупателей, ссылающих на этот адрес */
	public Set<Customer> getCustomers() {
		Set<Customer> customers = new HashSet<>();
		customers.addAll(customers_actual_adr);
		customers.addAll(customers_registred_adr);

		return customers;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", contry='" + contry + '\'' +
				", region='" + region + '\'' +
				", city='" + city + '\'' +
				", street='" + street + '\'' +
				", house='" + house + '\'' +
				", flat='" + flat + '\'' +
				", created=" + created +
				", modified=" + modified +
				'}';
	}
}
