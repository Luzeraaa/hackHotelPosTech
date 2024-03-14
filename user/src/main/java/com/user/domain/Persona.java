package com.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_persona")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;
  private String name;
  private String surname;
  private String email;
  private String password;
  private Integer ddd;
  private Integer phone;
  private String birthdate;
  private String address;
  @Enumerated(EnumType.STRING)
  private Country country;

  public Persona(String name, String surname, String email, String password, Integer ddd, Integer phone, String birthdate, String address, Country country) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.ddd = ddd;
    this.phone = phone;
    this.birthdate = birthdate;
    this.address = address;
    this.country = country;
  }
}
