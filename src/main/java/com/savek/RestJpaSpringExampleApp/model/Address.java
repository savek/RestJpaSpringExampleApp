package com.savek.RestJpaSpringExampleApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@JsonIgnore
	@Column(nullable = false)
	private Timestamp created;

	@JsonIgnore
	@Column(nullable = false)
	private Timestamp modified;

	/** Поле игнорируется при валидации схемы */
	@Transient
	@JsonIgnore
	private Integer ignoredField;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "actual_address_id", referencedColumnName = "id")
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private Set<Customer> customers_actual_adr;

	@JsonIgnore
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
		this.created = getSysDate();
		this.modified = getSysDate();
	}

	/**
	 * Значение даты "по дефолту" (sysdate)
	 * */
	public static Timestamp getSysDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Установка значений полей дат "Дата создания" и "Дата изменения"
	 * */
	public void setDefaultDates() {
		created = getSysDate();
		modified = getSysDate();
	}

	/**
	 * Возврат списка покупателей, ссылающих на этот адрес.
	 * На тот случай, если на этот адрес никто не ссылается, возвращается пустая коллекция.
	 * */
	@JsonIgnore
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;

		Address address = (Address) o;

		if (id != null ? !id.equals(address.id) : address.id != null) return false;
		if (contry != null ? !contry.equals(address.contry) : address.contry != null) return false;
		if (region != null ? !region.equals(address.region) : address.region != null) return false;
		if (city != null ? !city.equals(address.city) : address.city != null) return false;
		if (street != null ? !street.equals(address.street) : address.street != null) return false;
		if (house != null ? !house.equals(address.house) : address.house != null) return false;
		if (flat != null ? !flat.equals(address.flat) : address.flat != null) return false;
		if (created != null ? !created.equals(address.created) : address.created != null) return false;
		return modified != null ? modified.equals(address.modified) : address.modified == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (contry != null ? contry.hashCode() : 0);
		result = 31 * result + (region != null ? region.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (street != null ? street.hashCode() : 0);
		result = 31 * result + (house != null ? house.hashCode() : 0);
		result = 31 * result + (flat != null ? flat.hashCode() : 0);
		result = 31 * result + (created != null ? created.hashCode() : 0);
		result = 31 * result + (modified != null ? modified.hashCode() : 0);
		return result;
	}
}
