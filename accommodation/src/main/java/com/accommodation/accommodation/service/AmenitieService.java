package com.accommodation.accommodation.service;

import com.accommodation.accommodation.model.Amenitie;
import com.accommodation.accommodation.model.Amenities;
import com.accommodation.accommodation.repository.AccommodationRepository;
import com.accommodation.accommodation.repository.AmeniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenitieService {


    @Autowired
    private AmeniteRepository repository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    public List<Amenitie> registerAmenitie(Amenities amenities, Long idAccommodation) {

        var accommodation = accommodationRepository.findById(idAccommodation).orElseThrow(() -> new RuntimeException("Accommodation not found"));

        amenities.getAmenities().forEach(a -> {
            if (repository.findById(a.getId()).isPresent()) {
                throw new RuntimeException("Amenitie " + a.getName() + "  alredys exist");
            }
        });

        accommodation.setAmenities(amenities.getAmenities());

        return accommodationRepository.save(accommodation).getAmenities();
    }




}
