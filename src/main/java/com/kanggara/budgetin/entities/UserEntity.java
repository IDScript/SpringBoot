package com.kanggara.budgetin.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(name = "UniqueToken", columnNames = { "token" }) })
public class UserEntity {

	@Id
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(unique = true)
	private String token;

	@Column(name = "token_expiried_at")
	private Long tokenExpiriedAt;

	@OneToMany(mappedBy = "user")
	private List<ContactEntity> contacts;
}
