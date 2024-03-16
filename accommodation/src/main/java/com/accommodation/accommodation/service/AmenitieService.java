package com.accommodation.accommodation.service;

import com.accommodation.accommodation.controllers.dto.AmenitieUpdateDTO;
import com.accommodation.accommodation.model.Amenitie;
import com.accommodation.accommodation.repository.LocationRepository;
import com.accommodation.accommodation.repository.AmenitieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenitieService {

    @Autowired
    private AmenitieRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    public List<Amenitie> registerAmenitie(List<Amenitie> amenities, Long idAccommodation) {
        var accommodation = locationRepository.findById(idAccommodation)
                .orElseThrow(() -> new EntityNotFoundException("Accommodation not found"));

        amenities.forEach(a -> {
            if (repository.findByName(a.getName()) != null) {
                throw new RuntimeException("Amenitie " + a.getName() + " already exists");
            }
            a.setAccommodation(accommodation);
        });

        repository.saveAll(amenities);
        return locationRepository.save(accommodation).getAmenities();
    }

    public List<Amenitie> getAmenitieByAccommodation(Long idAccommodation) {
        return repository.getAllByAccommodationId(idAccommodation);
    }

    public Amenitie updateAmenitie(AmenitieUpdateDTO dto, Long idAmenitie) {

        Optional<Amenitie> existingAmenitie = repository.findById(idAmenitie);

        if (existingAmenitie.isEmpty()) {
            throw new EntityNotFoundException("Accommodation not found");
        }

        var amenitie = existingAmenitie.get();
        amenitie.update(dto);
        return repository.save(amenitie);

    }

    public void deleteAmenitie(Long id) {
        repository.findById(id).ifPresentOrElse(
                a -> repository.delete(a),
                () -> {
                    throw new EntityNotFoundException("Amenitie not foud");
                }
        );
    }
}
