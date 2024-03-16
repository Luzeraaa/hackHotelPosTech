package com.accommodation.accommodation.model;

import com.accommodation.accommodation.controllers.dto.AmenitieUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "amenitie")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amenitie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer amount;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;

    public void update(final AmenitieUpdateDTO dto) {
        if (dto.name() != null) {
            this.setName(dto.name());
        }

        if (dto.amount() != null) {
            this.setAmount(dto.amount());
        }

        if (dto.description() != null) {
            this.setDescription(dto.description());
        }
    }
}
