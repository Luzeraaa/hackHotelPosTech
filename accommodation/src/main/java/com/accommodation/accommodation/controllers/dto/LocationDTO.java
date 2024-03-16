package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationDTO(

        @NotBlank
        String name,

        @NotNull
        AddressDTO address
){}
