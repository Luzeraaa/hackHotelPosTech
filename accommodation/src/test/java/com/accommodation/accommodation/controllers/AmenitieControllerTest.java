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
        // Mocking input data
        List<AmenitieDTO> amenitieDTOList = new ArrayList<>();
        amenitieDTOList.add(new AmenitieDTO("Piscina", 5, "Piscina aquecida"));
        AmenitiesDTO dto = new AmenitiesDTO(amenitieDTOList);
        Long idAccommodation = 1L;

        // Mocking expected response
        List<Amenitie> expectedAmenities = new ArrayList<>();

        // Mocking service method behavior
        when(amenitieService.registerAmenitie(anyList(), (Mockito.anyLong()))).thenReturn(expectedAmenities);

        // Calling the controller method
        ResponseEntity<List<Amenitie>> responseEntity = amenitieController.registarAmenitie(dto, idAccommodation);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedAmenities, responseEntity.getBody());
    }

    @Test
    void testGetAmenitieByAccommodation() {
        // Mocking input data
        Long idAccommodation = 1L;

        // Mocking expected response
        List<Amenitie> expectedAmenities = new ArrayList<>();

        // Mocking service method behavior
        when(amenitieService.getAmenitieByAccommodation(Mockito.anyLong())).thenReturn(expectedAmenities);

        // Calling the controller method
        ResponseEntity<List<Amenitie>> responseEntity = amenitieController.registarAmenitie(idAccommodation);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedAmenities, responseEntity.getBody());
    }

    @Test
    void testUpdateAmenitie() {
        // Mocking input data
        AmenitieUpdateDTO dto = new AmenitieUpdateDTO("Piscina", 5, "Piscina aquecida");
        Long idAmenitie = 1L;

        // Mocking expected response
        Amenitie expectedAmenitie = new Amenitie(/* provide necessary parameters */);

        // Mocking service method behavior
        when(amenitieService.updateAmenitie(Mockito.any(), Mockito.anyLong())).thenReturn(expectedAmenitie);

        // Calling the controller method
        ResponseEntity<Amenitie> responseEntity = amenitieController.updateAmenitie(dto, idAmenitie);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedAmenitie, responseEntity.getBody());
    }

    @Test
    void testDeleteAmenitie() {
        // Mocking input data
        Long idAmenitie = 1L;

        // Mocking service method behavior
        doNothing().when(amenitieService).deleteAmenitie(Mockito.anyLong());

        // Calling the controller method
        ResponseEntity<Void> responseEntity = amenitieController.updateAmenitie(idAmenitie);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}