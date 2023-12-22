package com.kanggara.budgetin.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class ContactEntity {

	@Id
	private String id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String phone;

	@ManyToOne
	@JoinColumn(name = "username", referencedColumnName = "username", foreignKey = @ForeignKey(name = "fk_user_contact"))
	private UserEntity user;

	@OneToMany(mappedBy = "contact")
	private List<AddressEntity> addresses;
}
