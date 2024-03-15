package com.accommodation.accommodation.repository;

import com.accommodation.accommodation.model.Amenitie;
import com.accommodation.accommodation.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByName(String name);

    List<Building> getAllByAccommodationId(Long idAccommodation);
}
