package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotBlank;

public record BuildingDTO(

        @NotBlank
        String name

) {
}
