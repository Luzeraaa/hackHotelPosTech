package com.accommodation.accommodation.controllers.dto;

public record AddressUpdateDTO(

        String zipCode,

        String street,

        Integer number,

        String neighborhood,

        String city,

        String state,

        String reference
) {
}
