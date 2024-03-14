package com.user.domain;

import com.user.dto.UserDTO;
import jakarta.persistence.Entity;
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


  private Integer cpf;
  private Integer passport;


  public User(UserDTO userDTO) {
    super(userDTO.name(),
            userDTO.surname(),
            userDTO.email(),
            userDTO.password(),
            userDTO.ddd(),
            userDTO.phone(),
            userDTO.birthDate(),
            userDTO.address(),
            userDTO.country()
    );
    this.cpf = userDTO.cpf();
    this.passport = userDTO.passport();
  }
}
