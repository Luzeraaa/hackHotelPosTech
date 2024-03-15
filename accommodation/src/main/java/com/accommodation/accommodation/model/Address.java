package com.accommodation.accommodation.model;

import com.accommodation.accommodation.controllers.dto.AddressUpdateDTO;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String zipCode;
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
    private String reference;

    public void update(AddressUpdateDTO dto) {

        if (dto.zipCode() != null) {
            this.zipCode = dto.zipCode();
        }
        if (dto.street() != null) {
            this.street = dto.street();
        }
        if (dto.number() != null) {
            this.number = dto.number();
        }
        if (dto.neighborhood() != null) {
            this.neighborhood = dto.neighborhood();
        }
        if (dto.city() != null) {
            this.city = dto.city();
        }
        if (dto.state() != null) {
            this.state = dto.state();
        }
        if (dto.reference() != null) {
            this.reference = dto.reference();
        }
    }
}
