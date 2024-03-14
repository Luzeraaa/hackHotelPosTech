package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AmenitiesDTO(

        @NotNull
        List<AmenitieDTO> amenities

) {
}
