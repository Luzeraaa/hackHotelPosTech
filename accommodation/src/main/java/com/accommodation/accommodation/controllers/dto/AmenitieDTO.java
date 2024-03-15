package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AmenitieDTO(
        @NotBlank
        String name,

        @NotNull
        @Positive
        Integer amount,

        @NotNull
        String description


) {
}
