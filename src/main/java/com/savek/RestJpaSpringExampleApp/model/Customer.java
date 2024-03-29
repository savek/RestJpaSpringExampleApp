package com.savek.RestJpaSpringExampleApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savek.RestJpaSpringExampleApp.model.enums.Sex;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer implements Serializable {

	public Customer(String lastName,
					String firstName,
					String middleName,
					Address registredAddressId,
					Address actualAddressId,
					Sex sex) {
		this.registredAddressId = registredAddressId.getId();
		this.actualAddressId = actualAddressId.getId();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.sex = sex;
	}

	public Customer(String lastName,
					String firstName,
					String middleName,
					Long registredAddressId,
					Long actualAddressId,
					Sex sex) {
		this.registredAddressId = registredAddressId;
		this.actualAddressId = actualAddressId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.sex = sex;
	}

	@Id()
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(name = "registred_address_id", nullable = false)
	private Long registredAddressId;

	@Column(name = "actual_address_id", nullable = false)
	private Long actualAddressId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@Column(name = "sex", nullable = false, columnDefinition = "VARCHAR(6) CHECK (sex IN ('MALE', 'FEMALE'))")
	@Enumerated(value = EnumType.STRING)
	private Sex sex;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "registred_address_id",
				insertable = false,
				updatable = false,
				foreignKey = @ForeignKey(name = "fk_registred_address_id"))
	@Setter(AccessLevel.NONE)
	private Address registredAddress;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "actual_address_id",
				insertable = false,
				updatable = false,
				foreignKey = @ForeignKey(name = "fk_actual_address_id"))
	@Setter(AccessLevel.NONE)
	private Address actualAddress;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Customer customer = (Customer) o;

		if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
		if (registredAddressId != null ? !registredAddressId.equals(customer.registredAddressId) : customer.registredAddressId != null)
			return false;
		if (actualAddressId != null ? !actualAddressId.equals(customer.actualAddressId) : customer.actualAddressId != null)
			return false;
		if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
		if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
		if (middleName != null ? !middleName.equals(customer.middleName) : customer.middleName != null) return false;
		return sex == customer.sex;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (registredAddressId != null ? registredAddressId.hashCode() : 0);
		result = 31 * result + (actualAddressId != null ? actualAddressId.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		return result;
	}
}
