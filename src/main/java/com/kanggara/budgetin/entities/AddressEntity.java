package com.kanggara.budgetin.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class AddressEntity {

  @Id
  private String id;

  private String street;

  private String city;

  private String province;

  @Column(nullable = false)
  private String country;

  @Column(name = "postal_code")
  private String postalCode;

  @ManyToOne(optional = false)
  @JoinColumn(name = "contact_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_contact_id"))
  private ContactEntity contact;
}
