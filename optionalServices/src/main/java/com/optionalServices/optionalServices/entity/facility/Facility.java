package com.optionalServices.optionalServices.entity.facility;

import com.optionalServices.optionalServices.dto.FacilityDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Currency;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Currency price;

    public Facility(FacilityDTO facilityDTO) {
        this.name = facilityDTO.name();
        this.price = facilityDTO.price();
    }

    public void update(FacilityDTO dto){
        if (dto.name() != null) {
            this.name = dto.name();
        }

        if (dto.price() != null) {
            this.price = dto.price();
        }

    }
}
