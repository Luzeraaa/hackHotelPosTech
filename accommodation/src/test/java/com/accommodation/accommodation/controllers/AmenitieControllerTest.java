package com.accommodation.accommodation.controllers;

import com.accommodation.accommodation.controllers.dto.AmenitieDTO;
import com.accommodation.accommodation.controllers.dto.AmenitieUpdateDTO;
import com.accommodation.accommodation.controllers.dto.AmenitiesDTO;
import com.accommodation.accommodation.model.Amenitie;
import com.accommodation.accommodation.service.AmenitieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class AmenitieControllerTest {

    @Mock
    private AmenitieService amenitieService;

    @InjectMocks
    private AmenitieController amenitieController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegistrarAmenitie() {
        List<AmenitieDTO> amenitieDTOList = new ArrayList<>();
        amenitieDTOList.add(new AmenitieDTO("Piscina", 5, "Piscina aquecida"));
        AmenitiesDTO dto = new AmenitiesDTO(amenitieDTOList);
        Long idAccommodation = 1L;
        List<Amenitie> expectedAmenities = new ArrayList<>();
        when(amenitieService.registerAmenitie(anyList(), (Mockito.anyLong()))).thenReturn(expectedAmenities);
        ResponseEntity<List<Amenitie>> responseEntity = amenitieController.registarAmenitie(dto, idAccommodation);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedAmenities, responseEntity.getBody());
    }

    @Test
    void testGetAmenitieByAccommodation() {
        Long idAccommodation = 1L;
        List<Amenitie> expectedAmenities = new ArrayList<>();
        when(amenitieService.getAmenitieByAccommodation(Mockito.anyLong())).thenReturn(expectedAmenities);
        ResponseEntity<List<Amenitie>> responseEntity = amenitieController.registarAmenitie(idAccommodation);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedAmenities, responseEntity.getBody());
    }

    @Test
    void testUpdateAmenitie() {
        AmenitieUpdateDTO dto = new AmenitieUpdateDTO("Piscina", 5, "Piscina aquecida");
        Long idAmenitie = 1L;
        Amenitie expectedAmenitie = new Amenitie();
        when(amenitieService.updateAmenitie(Mockito.any(), Mockito.anyLong())).thenReturn(expectedAmenitie);
        ResponseEntity<Amenitie> responseEntity = amenitieController.updateAmenitie(dto, idAmenitie);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedAmenitie, responseEntity.getBody());
    }

    @Test
    void testDeleteAmenitie() {
        Long idAmenitie = 1L;
        doNothing().when(amenitieService).deleteAmenitie(Mockito.anyLong());
        ResponseEntity<Void> responseEntity = amenitieController.updateAmenitie(idAmenitie);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}