package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record AddressDTO(

        @NotNull(message = FIELD_CANNOT_BE_NULL_OR_EMPTY_MESSAGE)
        @Pattern(regexp = ZIP_CODE_REGEX, message = ZIP_CODE_INVALID_MESSAGE)
        String zipCode,

        @NotBlank(message = FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE)
        String street,

        @NotNull(message = FIELD_CANNOT_BE_NULL_OR_EMPTY_MESSAGE)
        @PositiveOrZero(message = FIELD_ONLY_NUMBER_MESSAGE)
        Integer number,

        @NotBlank(message = FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE)
        String neighborhood,

        @NotBlank(message = FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE)
        String city,

        @NotBlank(message = FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE)
        String state,

        String reference
){

private static final String ZIP_CODE_REGEX = "\\d{5}(-\\d{3})?";
private static final String ZIP_CODE_INVALID_MESSAGE = "Zipcode is invalid, example: 00000-000";
private static final String FIELD_CANNOT_BE_NULL_OR_EMPTY_MESSAGE = "Field cannot be null or empty";
private static final String FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE = "Field cannot be null, empty or blank";
private static final String FIELD_ONLY_NUMBER_MESSAGE = "Invalid field, enter only positive numbers";
        }
