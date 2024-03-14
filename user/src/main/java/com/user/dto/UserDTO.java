package com.user.dto;

import com.user.domain.Country;

public record UserDTO(
        String name,
        String surname,
        String email,
        String password,
        Integer ddd,
        Integer phone,
        String birthDate,
        String address,
        Country country,
        Integer cpf,
        Integer passport

) {
}
