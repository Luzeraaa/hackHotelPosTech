package com.accommodation.accommodation.service;

import com.accommodation.accommodation.controllers.dto.RoomUpdateDTO;
import com.accommodation.accommodation.model.Room;
import com.accommodation.accommodation.model.enums.RoomType;
import com.accommodation.accommodation.repository.BuildingRepository;
import com.accommodation.accommodation.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Room> registerRooms(List<Room> rooms, Long idBuilding) {
        var building = buildingRepository.findById(idBuilding)
                .orElseThrow(() -> new EntityNotFoundException("Building not found"));

        checkSameRooms(rooms);

        rooms.forEach(r -> {
            if (repository.findFirstByRoomTypeAndBuildingId(r.getRoomType(), idBuilding) != null) {
                throw new RuntimeException("Room " + r.getRoomType() + " already exists for this building");
            }
            r.setBuilding(building);
        });

        repository.saveAll(rooms);
        return buildingRepository.save(building).getRooms();
    }

    private void checkSameRooms(List<Room> rooms) {
        var distinctListSize = rooms.stream().map(r -> RoomType.valueOf(r.getRoomType().toString())).collect(Collectors.toSet()).size();
        if (rooms.size() > distinctListSize) {
            throw new RuntimeException("There are the same rooms in the list");
        }
    }

    public List<Room> getAllRoomsByBuilding(Long idBuilding) {
        return repository.findAllByBuildingId(idBuilding);
    }

    public Room updateRoom(RoomUpdateDTO dto, Long idRoom) {

        Optional<Room> room = repository.findById(idRoom);

        if (room.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        room.get().update(dto);

        return repository.save(room.get());
    }

    public void deleteRoom(Long id) {
        repository.findById(id).ifPresentOrElse(
                a -> repository.delete(a),
                () -> {
                    throw new RuntimeException("Room not foud");
                }
        );
    }
}
