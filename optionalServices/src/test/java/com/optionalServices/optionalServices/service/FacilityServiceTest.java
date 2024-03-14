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
        long id = 1L;
        Facility expectedFacility = new Facility();
        when(facilityRepository.findById(id)).thenReturn(Optional.of(expectedFacility));
        Optional<Facility> result = facilityService.getFacilityById(id);
        verify(facilityRepository).findById(id);
        assertTrue(result.isPresent());
        assertEquals(expectedFacility, result.get());
    }

    @Test
    void testRegisterFacility() {
        FacilityDTO facilityDTO = new FacilityDTO("Sample Facility", 100.0);
        Facility expectedFacility = new Facility(facilityDTO);
        when(facilityRepository.save(any(Facility.class))).thenReturn(expectedFacility);
        Facility result = facilityService.registerFacility(facilityDTO);
        verify(facilityRepository).save(argThat(facility ->
                facility.getName().equals(facilityDTO.name()) &&
                        facility.getPrice() == facilityDTO.price()
        ));
        assertEquals(expectedFacility, result);
    }

    @Test
    void testGetFacilityByName() {
        String name = "Sample Facility";
        List<Facility> expectedFacilities = Arrays.asList(new Facility(), new Facility());
        List<Facility> result = facilityService.getFacilityByName(name);
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
        Long id = 1L;
        FacilityDTO facilityDTO = new FacilityDTO("Updated Name", 123.45);
        Facility existingFacility = mock(Facility.class);
        when(facilityRepository.findById(id)).thenReturn(Optional.of(existingFacility));
        facilityService.updateFacility(id, facilityDTO);
        verify(existingFacility).update(facilityDTO);
        when(facilityRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> facilityService.updateFacility(id, facilityDTO));
    }
}