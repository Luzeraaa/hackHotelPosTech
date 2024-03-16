package com.accommodation.accommodation.service;

import com.accommodation.accommodation.controllers.dto.AmenitieUpdateDTO;
import com.accommodation.accommodation.model.Building;
import com.accommodation.accommodation.repository.BuildingRepository;
import com.accommodation.accommodation.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {


    @Autowired
    private BuildingRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    public List<Building> registerBuilding(List<Building> buildings, Long idLocation) {
        var location = locationRepository.findById(idLocation)
                .orElseThrow(() -> new EntityNotFoundException("Accommodation not found"));

        buildings.forEach(b -> {
            if (repository.findByNameAndLocationId(b.getName(), idLocation) != null) {
                throw new RuntimeException("Building " + b.getName() + " already exists");
            }
            b.setLocation(location);
        });

        repository.saveAll(buildings);

        return locationRepository.save(location).getBuildings();
    }

    public List<Building> getBuildingByAccommodation(Long idAccommodation) {
        return repository.getAllByLocationId(idAccommodation);

    }

    public Building updateBuilding(AmenitieUpdateDTO dto, Long idBuilding) {

        Optional<Building> building = repository.findById(idBuilding);

        if (building.isEmpty()) {
            throw new EntityNotFoundException("Building not found");
        }

        building.get().update(dto);

        return repository.save(building.get());
    }

    public void deleteBuilding(Long id) {
        repository.findById(id).ifPresentOrElse(
                a -> repository.delete(a),
                () -> {
                    throw new EntityNotFoundException("Building not foud");
                }
        );
    }
}
