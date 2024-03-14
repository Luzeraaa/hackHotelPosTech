package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AccomodationDTO(

        @NotBlank
        String name,

        @NotNull
        AddressDTO address
){}
