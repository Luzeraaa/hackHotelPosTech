package com.accommodation.accommodation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "accommodation")
@Entity
@EqualsAndHashCode(of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Address address;


    @OneToMany(mappedBy = "amenities", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Amenitie> amenities;

    @OneToMany(mappedBy = "amenities", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Building> buildings;

}
