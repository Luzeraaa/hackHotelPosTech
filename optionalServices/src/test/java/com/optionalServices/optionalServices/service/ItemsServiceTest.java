package com.optionalServices.optionalServices.service;

import com.optionalServices.optionalServices.dto.ItemsDTO;
import com.optionalServices.optionalServices.entity.items.Items;
import com.optionalServices.optionalServices.repository.items.ItemsRepository;
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
class ItemsServiceTest {

    @Mock
    private ItemsRepository itemsRepository;

    @InjectMocks
    private ItemsService itemsService;

    @Test
    void getAllItems() {
    }

    @Test
    void testGetItemsById() {
        // Mock data
        long id = 1L;
        Items expectedFacility = new Items(); // Mock the expected facility object returned by the repository

        // Stub the behavior of the findById method to return the expected facility wrapped in Optional
        when(itemsRepository.findById(id)).thenReturn(Optional.of(expectedFacility));

        // Call the method to be tested
        Optional<Items> result = itemsService.getItemsById(id);

        // Verify that the findById method is called on the repository with the correct id parameter
        verify(itemsRepository).findById(id);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(expectedFacility, result.get());
    }

    @Test
    void testRegisterItems() {
        // Mock data
        ItemsDTO itemsDTO = new ItemsDTO("Sample Name", 10.5); // Mock itemsDTO
        Items expectedItems = new Items(itemsDTO); // Mock expected items returned by the repository

        // Stub the behavior of save method
        when(itemsRepository.save(new Items(itemsDTO))).thenReturn(expectedItems);

        // Call the method to be tested
        Items result = itemsService.registerItems(itemsDTO);

        // Assertions
        assertEquals(expectedItems, result);

        // Verify that the save method is called on the repository with the correct itemsDTO parameter
        verify(itemsRepository).save(new Items(itemsDTO));
    }

    @Test
    void testGetItemsByName() {
        // Mock data
        String name = "Sample Items";
        List<Items> expectedFacilities = Arrays.asList(new Items(), new Items()); // Mock list of facilities

        // Stub the behavior of findByName method
        when(itemsRepository.findByName(name)).thenReturn(expectedFacilities);

        // Call the method to be tested
        List<Items> result = itemsService.getItemsByName(name);

        // Assertions
        verify(itemsRepository).findByName(name);
        assertEquals(expectedFacilities, result);
    }

    @Test
    void deleteItems() {
    }

    @Test
    void testUpdateItems() {
        // Mock data
        Long id = 1L;
        ItemsDTO itemsDTO = new ItemsDTO("Updated Name", 123.45);
        Items existingItems = mock(Items.class); // Mock existing facility

        // Scenario 1: Facility exists
        // Stub the behavior of findById method
        when(itemsRepository.findById(id)).thenReturn(Optional.of(existingItems));

        // Call the method to be tested
        itemsService.updateItems(id, itemsDTO);

        // Verify that the update method is called on the existing facility
        verify(existingItems).update(itemsDTO);

        // Scenario 2: Facility does not exist
        // Stub the behavior of findById method to return empty optional
        when(itemsRepository.findById(id)).thenReturn(Optional.empty());

        // Call the method to be tested and assert that it throws EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> itemsService.updateItems(id, itemsDTO));
    }
}