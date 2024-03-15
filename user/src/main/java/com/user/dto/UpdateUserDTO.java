package com.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.domain.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        Integer ddd,
        @NotNull
        Integer phone,
        @NotBlank
        String birthdate,
        @NotBlank
        String address,
        @NotNull
        Country country,
        @NotNull
        Integer cpf,
        @NotNull
        Integer passport
) {
  @JsonCreator
  public UpdateUserDTO(
          @JsonProperty("name") String name,
          @JsonProperty("surname") String surname,
          @JsonProperty("email") String email,
          @JsonProperty("password") String password,
          @JsonProperty("ddd") Integer ddd,
          @JsonProperty("phone") Integer phone,
          @JsonProperty("birthdate") String birthdate,
          @JsonProperty("address") String address,
          @JsonProperty("country") String country,
          @JsonProperty("cpf") Integer cpf,
          @JsonProperty("passport") Integer passport
  ) {
    this(name, surname, email, password, ddd, phone, birthdate, address, Country.convertStringToCategory(country), cpf, passport);
  }
}
