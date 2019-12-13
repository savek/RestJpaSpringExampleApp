package com.savek.RestJpaSpringExampleApp.model;

import com.savek.RestJpaSpringExampleApp.model.enums.Sex;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer implements Serializable {

	public Customer(Long registredAddresId,
	                Long actualAddressId,
	                String firstName,
	                String lastName,
	                String middleName,
	                Sex sex) {
		this.registredAddresId = registredAddresId;
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
	private Long registredAddresId;

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "registred_address_id",
				insertable = false,
				updatable = false,
				foreignKey = @ForeignKey(name = "fk_registred_address_id"))
	@Setter(AccessLevel.NONE)
	private Address registredAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "actual_address_id",
				insertable = false,
				updatable = false,
				foreignKey = @ForeignKey(name = "fk_actual_address_id"))
	@Setter(AccessLevel.NONE)
	private Address actualAddress;
}
