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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

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
        List<Facility> data = Arrays.asList(
                new Facility(123L, "cafe da manha", 125.30),
                new Facility(123L, "massagem", 100.00),
                new Facility(123L, "garçom", 150.00)
            );
        PageRequest pageRequest = PageRequest.of(0, data.size());
        var page = new PageImpl<>(data, pageRequest, data.size());

        // Mock behavior
        when(facilityService.getAllFacility(anyInt(), anyInt())).thenReturn((new Pagination<Facility>(page)) );

        // Call the method to be tested
        ResponseEntity<Pagination<Facility>> responseEntity = facilityController.getAllFacility(10, 0);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 for OK
        assertNotNull(responseEntity.getBody());
        assertEquals(data.size(), responseEntity.getBody().getTotal());
        assertEquals(0, responseEntity.getBody().getOffset());
        assertEquals(3, responseEntity.getBody().getLimit());

        // Verify that the service method was called with the correct arguments
        verify(facilityService).getAllFacility(10, 0);
    }

    @Test
    void testGetFacilityById() {
        // Mock data
        long facilityId = 123L;
        Facility mockFacility = new Facility(facilityId, "Test Facility", 100.0);
        Optional<Facility> optionalFacility = Optional.of(mockFacility);

        // Mock service method
        when(facilityService.getFacilityById(facilityId)).thenReturn(optionalFacility);

        // Call the method to be tested
        ResponseEntity<Optional<Facility>> responseEntity = facilityController.getFacilityById(facilityId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(optionalFacility, responseEntity.getBody());

        // Verify that the service method was called with the correct argument
        verify(facilityService).getFacilityById(facilityId);
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
        ResponseEntity<Facility> responseEntity = facilityController.registerFacility(facilityDTO, uriBuilder);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Check that the location header contains the expected URI with the generated ID
        assertEquals("/facility", responseEntity.getHeaders().getLocation().getPath());

        // Verify that the service method was called with the correct argument
        verify(facilityService).registerFacility(facilityDTO);
    }

    @Test
    void testUpdateFacility() {
        // Mock data
        Long facilityId = 123L;
        FacilityDTO facilityDTO = new FacilityDTO("Updated Facility", 200.0);

        // Call the method to be tested
        ResponseEntity<String> responseEntity = facilityController.updateFacility(facilityDTO, facilityId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Facility updated successfully", responseEntity.getBody());

        // Verify that the service method was called with the correct arguments
        verify(facilityService).updateFacility(facilityId, facilityDTO);
    }

    @Test
    void testGetServicesByName() {
        // Mock data
        String serviceName = "Test Service";
        List<Facility> mockedServices = new ArrayList<>();
        mockedServices.add(new Facility(1L, serviceName, 100.0));
        mockedServices.add(new Facility(2L, serviceName, 200.0));

        // Mock service method
        when(facilityService.getServicesByName(serviceName)).thenReturn(mockedServices);

        // Call the method to be tested
        ResponseEntity<List<Facility>> responseEntity = facilityController.getServicesByName(serviceName);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedServices, responseEntity.getBody());

        // Verify that the service method was called with the correct argument
        verify(facilityService).getServicesByName(serviceName);
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

}