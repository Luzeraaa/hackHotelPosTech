package com.accommodation.accommodation.service;

import com.accommodation.accommodation.controllers.dto.AccommodationUpdateDTO;
import com.accommodation.accommodation.model.Location;
import com.accommodation.accommodation.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public Location registerLocation(Location accommodation) {
        if (locationRepository.findByName(accommodation.getName()) != null) {
            throw new RuntimeException("Location alredys exist");
        }
        return locationRepository.save(accommodation);
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    @Transactional
    public void deleteLocation(Long id) {
        locationRepository.findById(id).ifPresentOrElse(
                a -> locationRepository.delete(a),
                () -> {
                    throw new EntityNotFoundException("Location not foud");
                }
        );
    }

    @Transactional
    public Location updateLocation(AccommodationUpdateDTO dto, Long id) {
        Optional<Location> location = locationRepository.findById(id);

        if (location.isEmpty()) {
            throw new EntityNotFoundException("Accommodation not found");
        }
        location.get().update(dto);
        return locationRepository.save(location.get());

    }
}
