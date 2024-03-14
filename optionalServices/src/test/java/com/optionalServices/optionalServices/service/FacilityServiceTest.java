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
    void testGetFacilityById() {
        // Mock data
        long id = 1L;
        Facility expectedFacility = new Facility(); // Mock the expected facility object returned by the repository

        // Stub the behavior of the findById method to return the expected facility wrapped in Optional
        when(facilityRepository.findById(id)).thenReturn(Optional.of(expectedFacility));

        // Call the method to be tested
        Optional<Facility> result = facilityService.getFacilityById(id);

        // Verify that the findById method is called on the repository with the correct id parameter
        verify(facilityRepository).findById(id);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(expectedFacility, result.get());
    }

    @Test
    void testRegisterFacility() {
        // Mock data
        FacilityDTO facilityDTO = new FacilityDTO("Sample Facility", 100.0);
        Facility expectedFacility = new Facility(facilityDTO); // Mock the expected facility object returned by the repository

        // Stub the behavior of the save method to return the expected facility
        when(facilityRepository.save(any(Facility.class))).thenReturn(expectedFacility);

        // Call the method to be tested
        Facility result = facilityService.registerFacility(facilityDTO);

        // Verify that the save method is called on the repository with the correct FacilityDTO parameter
        verify(facilityRepository).save(argThat(facility ->
                facility.getName().equals(facilityDTO.name()) &&
                        facility.getPrice() == facilityDTO.price()
        ));

        // Assertions
        assertEquals(expectedFacility, result);
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