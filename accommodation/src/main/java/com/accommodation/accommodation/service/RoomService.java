package com.accommodation.accommodation.service;

import com.accommodation.accommodation.controllers.dto.RoomUpdateDTO;
import com.accommodation.accommodation.model.Room;
import com.accommodation.accommodation.repository.BuildingRepository;
import com.accommodation.accommodation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Room> registerRooms(List<Room> rooms, Long idBuilding) {
        var building = buildingRepository.findById(idBuilding)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        rooms.forEach(r -> {
            if (repository.findByRoomTypeAndBuildingId(r.getRoomType(), idBuilding) != null) {
                throw new RuntimeException("Room " + r.getRoomType() + " already exists for this building");
            }
            r.setBuilding(building);
        });

        repository.saveAll(rooms);
        return buildingRepository.save(building).getRooms();
    }

    public List<Room> getAllRoomsByBuilding(Long idBuilding) {
        return repository.findAllByBuildingId(idBuilding);
    }

    public Room updateRoom(RoomUpdateDTO dto, Long idRoom) {

        Optional<Room> room = repository.findById(idRoom);

        if (room.isEmpty()) {
            throw new RuntimeException("Building not found");
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
