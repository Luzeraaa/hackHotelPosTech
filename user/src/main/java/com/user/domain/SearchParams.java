package com.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchParams {

  private final static Integer DEFAULT_LIMIT = 10;
  private final static Integer DEFAULT_OFFSET = 0;
  private final static boolean ALL = false;

  private Long id;
  private String name;
  private String surname;
  private String email;
  private Integer ddd;
  private Integer phone;
  private String birthdate;
  private String address;
  private Country country;
  private Integer cpf;
  private Integer passport;
  private Integer limit = DEFAULT_LIMIT;
  private Integer offset = DEFAULT_OFFSET;


  public Boolean hasParams() {
    return Stream.of(id, name, surname, email, ddd, phone, birthdate, address, country, cpf, passport)
            .anyMatch(Objects::nonNull);
  }
}
