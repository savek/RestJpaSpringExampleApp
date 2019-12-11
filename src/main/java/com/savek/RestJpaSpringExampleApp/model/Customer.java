package com.savek.RestJpaSpringExampleApp.model;

import com.savek.RestJpaSpringExampleApp.model.enums.Sex;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Customer {
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
	private Address registredAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "actual_address_id",
			insertable = false,
			updatable = false,
			foreignKey = @ForeignKey(name = "fk_actual_address_id"))
	private Address actualAddress;
}
