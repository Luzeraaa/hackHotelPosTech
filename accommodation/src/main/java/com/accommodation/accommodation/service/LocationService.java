package com.accommodation.accommodation.service;

import com.accommodation.accommodation.controllers.dto.AccommodationUpdateDTO;
import com.accommodation.accommodation.model.Location;
import com.accommodation.accommodation.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location registerAccommodation(Location accommodation) {
        if (locationRepository.findByName(accommodation.getName()) != null) {
            throw new RuntimeException("Location alredys exist");
        }
        return locationRepository.save(accommodation);
    }

    public List<Location> getAllAccommodations() {
        return locationRepository.findAll();
    }

    public void deleteAccommodation(Long id) {
        locationRepository.findById(id).ifPresentOrElse(
                a -> locationRepository.delete(a),
                () -> {
                    throw new EntityNotFoundException("Location not foud");
                }
        );
    }

    public Location updateAccommodation(AccommodationUpdateDTO dto, Long id) {
        Optional<Location> location = locationRepository.findById(id);

        if (location.isEmpty()) {
            throw new EntityNotFoundException("Accommodation not found");
        }
        location.get().update(dto);
        return locationRepository.save(location.get());

    }
}
