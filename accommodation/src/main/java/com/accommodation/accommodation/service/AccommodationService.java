package com.accommodation.accommodation.service;

import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {


    private AccommodationRepository accommodationRepository;

    public Accommodation registerAccommodation(Accommodation accommodation) {

        if (accommodationRepository.findById(accommodation.getId()).isPresent()) {
            throw new RuntimeException("Accommodation alredys exist");
        }
        return accommodationRepository.save(accommodation);
    }

    public List<Accommodation> getListAccommodations() {
        return accommodationRepository.findAll();
    }

    public void deleteAccommodation(Long id) {

        accommodationRepository.findById(id).ifPresentOrElse(
                a -> accommodationRepository.delete(a),
                () -> {
                    throw new RuntimeException("Accommodation not foud");
                }
        );
    }
}
