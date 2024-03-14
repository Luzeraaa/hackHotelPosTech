package com.accommodation.accommodation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "accommodation")
@Entity
@Data
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @Column
    @OneToMany(mappedBy = "accommodation", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Amenitie> amenities;

    @Column
    @OneToMany(mappedBy = "accommodation", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Building> buildings;
}
