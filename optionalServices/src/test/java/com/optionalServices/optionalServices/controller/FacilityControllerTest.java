package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.service.FacilityService;
import com.optionalServices.optionalServices.utils.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

class FacilityControllerTest {

    @Mock
    private FacilityService facilityService;

    @InjectMocks
    private FacilityController facilityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFacility() {
        // Mock data
        List<Facility> mockServices = new ArrayList<>();
        mockServices.add(new Facility(123L, "cafe da manha", 125.30));
        // Add more mock data as needed

        // Mock behavior
        when(facilityService.getAllFacility(anyInt(), anyInt())).thenReturn((Pagination<Facility>) mockServices);

        // Call the method to be tested
        ResponseEntity<Pagination<Facility>> responseEntity = facilityController.getAllFacility(10, 0);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 for OK
        assertNotNull(responseEntity.getBody());
        assertEquals(mockServices, responseEntity.getBody().getTotal());

        // Verify that the service method was called with the correct arguments
        verify(facilityService).getAllFacility(10, 0);
    }

    @Test
    void getFacilityById() {
    }

    @Test
    void testRegisterFacility() {
        // Mock data
        FacilityDTO facilityDTO = new FacilityDTO("cafe da manha", 123.30);
        Long generatedId = 123L; // Simulating the generated ID
        Facility registeredFacility = new Facility(generatedId, facilityDTO.name(), facilityDTO.price());
        when(facilityService.registerFacility(facilityDTO)).thenReturn(registeredFacility);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/");

        // Call the method to be tested
        ResponseEntity<Facility> responseEntity = facilityController.resgisterFacility(facilityDTO, uriBuilder);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Check that the location header contains the expected URI with the generated ID
        assertEquals("/" + generatedId, responseEntity.getHeaders().getLocation().getPath());

        // Verify that the service method was called with the correct argument
        verify(facilityService).registerFacility(facilityDTO);
    }

    @Test
    void updateFacility() {
    }

    @Test
    void getServicesByName() {
    }

    @Test
    void testDeleteFacility() {
        // Mock data
        Long idToDelete = 123L;

        // Mock behavior of the facilityService
        doNothing().when(facilityService).deleteFacility(idToDelete);

        // Call the method to be tested
        ResponseEntity<Facility> responseEntity = facilityController.deleteFacility(idToDelete);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called with the correct argument
        verify(facilityService).deleteFacility(idToDelete);
    }

    @Test
    void facilityService() {
    }
}