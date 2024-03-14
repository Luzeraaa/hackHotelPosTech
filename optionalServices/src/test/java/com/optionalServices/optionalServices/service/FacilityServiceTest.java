package com.optionalServices.optionalServices.service;

import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.repository.facility.FacilityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacilityServiceTest {

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    private FacilityService facilityService;


    @Test
    void getAllServices() {
    }

    @Test
    void getFacilityById() {
    }

    @Test
    void registerFacility() {
    }

    @Test
    void testGetFacilityByName() {
        // Mock data
        String name = "Sample Facility";
        List<Facility> expectedFacilities = Arrays.asList(new Facility(), new Facility()); // Mock list of facilities

        // Stub the behavior of findByName method
        when(facilityRepository.findByName(name)).thenReturn(expectedFacilities);

        // Call the method to be tested
        List<Facility> result = facilityService.getFacilityByName(name);

        // Assertions
        verify(facilityRepository).findByName(name);
        assertEquals(expectedFacilities, result);
    }

    @Test
    void testDeleteFacility() {
        // Mock data
        Long id = 1L;
        Facility facility = new Facility(); // Mock facility object

        // Stub the behavior of getFacilityById method to return the facility
        when(facilityRepository.findById(id)).thenReturn(Optional.of(facility));

        // Call the method to be tested
        facilityService.deleteFacility(id);

        // Verify that deleteById method is called on the repository with the correct id
        verify(facilityRepository).deleteById(id);
    }

    @Test
    void testUpdateFacility() {
        // Mock data
        Long id = 1L;
        FacilityDTO facilityDTO = new FacilityDTO("Updated Name", 123.45);
        Facility existingFacility = mock(Facility.class); // Mock existing facility

        // Scenario 1: Facility exists
        // Stub the behavior of findById method
        when(facilityRepository.findById(id)).thenReturn(Optional.of(existingFacility));

        // Call the method to be tested
        facilityService.updateFacility(id, facilityDTO);

        // Verify that the update method is called on the existing facility
        verify(existingFacility).update(facilityDTO);

        // Scenario 2: Facility does not exist
        // Stub the behavior of findById method to return empty optional
        when(facilityRepository.findById(id)).thenReturn(Optional.empty());

        // Call the method to be tested and assert that it throws EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> facilityService.updateFacility(id, facilityDTO));
    }
}