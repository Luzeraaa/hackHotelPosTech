package com.user.dto;

import com.user.domain.Country;
import com.user.exception.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.user.util.Validators.isNullOrEmptyOrBlank;

public record UserDTO(
        @NotBlank(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        @NotNull(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        String name,
        @NotBlank(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        @NotNull(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        String surname,
        @Email(message = EMAIL_MESSAGE)
        String email,
        @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_MESSAGE)
        String password,
        @NotNull(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        Integer ddd,
        @NotBlank(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        @NotNull(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        String phone,
        @NotBlank(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        @NotNull(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        @Pattern(regexp = BIRTHDATE_REGEX, message = BIRTHDATE_MESSAGE)
        String birthdate,
        String address,
        @NotNull(message = NOT_EMPTY_OR_BLANK_MESSAGE)
        Country country,
        String cpf,
        Integer passport

) {

  private static final String NOT_EMPTY_OR_BLANK_MESSAGE = "The field cannot be empty or blank";
  private static final String EMAIL_MESSAGE = "Email is invalid, Please use a valid email";
  private static final String PASSWORD_MESSAGE = "Password must contain at least 4 digits, 1 uppercase letter and 1 special character";
  private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d{4,})(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
  private static final String CPF_REQUIRED_MESSAGE = "CPF is required when country is BRAZIL OR CPF is invalid";
  private static final String PASSPORT_REQUIRED_MESSAGE = "Passport is required when country is not BRAZIL";
  private static final String BIRTHDATE_MESSAGE = "Birthdate must be in the format YYYY/MM/DD";
  private static final String BIRTHDATE_REGEX = "^\\d{4}/\\d{2}/\\d{2}$";
  private static final String CPF_REGEX = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";


  public void validUser() {
    validateCountry();
    validatePassport();
  }

  private void validateCountry() {
    if (isBrazil() && (isNullOrEmptyOrBlank(cpf) || !isCpfValid(cpf))) {
      throw new BadRequestException(CPF_REQUIRED_MESSAGE);
    }

  }

  private void validatePassport() {
    if (country != Country.BRAZIL && isNullOrEmptyOrBlank(passport)) {
      throw new BadRequestException(PASSPORT_REQUIRED_MESSAGE);
    }
  }

  private boolean isBrazil() {
    return country == Country.BRAZIL;
  }

  private boolean isCpfValid(String cpf) {
    return cpf != null && cpf.matches(CPF_REGEX);
  }

}
