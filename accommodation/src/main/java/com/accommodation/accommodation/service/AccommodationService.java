package com.accommodation.accommodation.service;

import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.repository.AccommodationRepository;
import com.accommodation.accommodation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {


    @Autowired
    private AccommodationRepository repository;

    @Autowired
    private RoomRepository roomRepository;


    public Accommodation registerAccommodation(Accommodation accommodation, Long idUsuario, Long idRoom) {

        checkData(accommodation);

        if (repository.findByIdUser(idUsuario).isPresent()) {
            throw new RuntimeException("Accommodation alredys existe for user");
        }

        var room = roomRepository.findById(idRoom).orElseThrow(() -> new RuntimeException("Room not found"));
        accommodation.setRoom(room);
        return repository.save(accommodation);
    }

    private void checkData(Accommodation accommodation) {
        if (!repository.findByCheckInBetween(accommodation.getCheckIn(), accommodation.getCheckOut()).isEmpty()) {
            throw new RuntimeException("There is already accommodation on that date");
        }
    }

}
