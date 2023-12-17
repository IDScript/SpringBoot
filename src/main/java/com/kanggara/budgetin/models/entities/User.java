package com.kanggara.budgetin.models.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    @Column(unique = true)
    private String token;

    @Column(name = "token_expiried_at")
    private Long tokenExpiriedAt;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;
}
