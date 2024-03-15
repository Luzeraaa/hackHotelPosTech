package com.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Getter
@Setter
@Table(name = "tb_user")
public class User extends Persona {

  private String name;
  private String surname;
  private String email;
  @JsonIgnore
  private String password;
  private Integer ddd;
  private String phone;
  private String birthdate;
  private String address;
  @Enumerated(EnumType.STRING)
  private Country country;
  private String cpf;
  private Integer passport;

  public User(UserDTO userDTO) {
    this.name = userDTO.name();
    this.surname = userDTO.surname();
    this.email = userDTO.email();
    this.password = userDTO.password();
    this.ddd = userDTO.ddd();
    this.phone = userDTO.phone();
    this.birthdate = userDTO.birthdate();
    this.address = userDTO.address();
    this.country = userDTO.country();
    this.cpf = userDTO.cpf();
    this.passport = userDTO.passport();
  }
}
