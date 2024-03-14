package com.optionalServices.optionalServices.service;

import com.optionalServices.optionalServices.dto.ItemsDTO;
import com.optionalServices.optionalServices.entity.items.Items;
import com.optionalServices.optionalServices.repository.items.ItemsRepository;
import com.optionalServices.optionalServices.utils.Pagination;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    void testGetAllItems() {
        int limit = 10;
        int offset = 0;
        List<Items> expectedItemsList = List.of(new Items(), new Items());
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Items> page = new PageImpl<>(expectedItemsList, pageable, expectedItemsList.size());
        when(itemsRepository.findAll(pageable)).thenReturn(page);
        Pagination<Items> result = itemsService.getAllItems(limit, offset);
        assertEquals(expectedItemsList.size(), result.getResults().size());
    }

    @Test
    void testGetItemsById() {
        long id = 1L;
        Items expectedFacility = new Items();
        when(itemsRepository.findById(id)).thenReturn(Optional.of(expectedFacility));
        Optional<Items> result = itemsService.getItemsById(id);
        verify(itemsRepository).findById(id);
        assertTrue(result.isPresent());
        assertEquals(expectedFacility, result.get());
    }

    @Test
    void testRegisterItems() {
        ItemsDTO itemsDTO = new ItemsDTO("Sample Name", 10.5);
        Items expectedItems = new Items(itemsDTO);
        when(itemsRepository.save(new Items(itemsDTO))).thenReturn(expectedItems);
        Items result = itemsService.registerItems(itemsDTO);
        assertEquals(expectedItems, result);
        verify(itemsRepository).save(new Items(itemsDTO));
    }

    @Test
    void testGetItemsByName() {
        String name = "Sample Items";
        List<Items> expectedFacilities = Arrays.asList(new Items(), new Items());
        when(itemsRepository.findByName(name)).thenReturn(expectedFacilities);
        List<Items> result = itemsService.getItemsByName(name);
        verify(itemsRepository).findByName(name);
        assertEquals(expectedFacilities, result);
    }

    @Test
    void testDeleteItems() {
        // Mock data
        Long id = 1L;
        Items items = new Items(); // Mock items object

        // Stub the behavior of getItemsById method
        when(itemsRepository.findById(id)).thenReturn(Optional.of(items)); // Assuming your repository method is findById

        // Call the method to be tested
        itemsService.deleteItems(id);

        // Verify that the deleteById method is called on the repository with the correct id parameter
        verify(itemsRepository).deleteById(id);
    }

    @Test
    void testUpdateItems() {
        Long id = 1L;
        ItemsDTO itemsDTO = new ItemsDTO("Updated Name", 123.45);
        Items existingItems = mock(Items.class);
        when(itemsRepository.findById(id)).thenReturn(Optional.of(existingItems));
        itemsService.updateItems(id, itemsDTO);
        verify(existingItems).update(itemsDTO);
        when(itemsRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> itemsService.updateItems(id, itemsDTO));
    }
}