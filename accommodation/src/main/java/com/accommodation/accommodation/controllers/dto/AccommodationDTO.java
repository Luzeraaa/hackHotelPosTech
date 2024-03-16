package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AccommodationDTO(

        @NotNull
        Date checkIn,

        @NotNull
        Date checkOut,

        @NotNull
        Integer totalPeople
) {
}
