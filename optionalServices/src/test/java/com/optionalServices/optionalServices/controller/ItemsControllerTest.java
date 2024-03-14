package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.dto.ItemsDTO;
import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.entity.items.Items;
import com.optionalServices.optionalServices.service.FacilityService;
import com.optionalServices.optionalServices.service.ItemsService;
import com.optionalServices.optionalServices.utils.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemsControllerTest {

    @Mock
    private ItemsService itemsService;

    @InjectMocks
    private ItemsController itemsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItems() {
        // Mock data
        List<Items> data = Arrays.asList(
                new Items(123L, "refrigerante", 5.30),
                new Items(123L, "porçao", 70.00),
                new Items(123L, "artesanato", 150.00)
        );
        PageRequest pageRequest = PageRequest.of(0, data.size());
        var page = new PageImpl<>(data, pageRequest, data.size());

        // Mock behavior
        when(itemsService.getAllItems(anyInt(), anyInt())).thenReturn((new Pagination<Items>(page)) );

        // Call the method to be tested
        ResponseEntity<Pagination<Items>> responseEntity = itemsController.getAllItems(10, 0);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 for OK
        assertNotNull(responseEntity.getBody());
        assertEquals(data.size(), responseEntity.getBody().getTotal());
        assertEquals(0, responseEntity.getBody().getOffset());
        assertEquals(3, responseEntity.getBody().getLimit());

        // Verify that the service method was called with the correct arguments
        verify(itemsService).getAllItems(10, 0);
    }

    @Test
    void testGetItemsById() {
        // Mock data
        long itemId = 123L;
        Items mockItems = new Items(itemId, "Test Items", 100.0);
        Optional<Items> optionalItems = Optional.of(mockItems);

        // Mock service method
        when(itemsService.getItemsById(itemId)).thenReturn(optionalItems);

        // Call the method to be tested
        ResponseEntity<Optional<Items>> responseEntity = itemsController.getItemsById(itemId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(optionalItems, responseEntity.getBody());

        // Verify that the service method was called with the correct argument
        verify(itemsService).getItemsById(itemId);
    }

    @Test
    void testRegisterItems() {
        // Mock data
        ItemsDTO itemsDTO = new ItemsDTO("cafe da manha", 123.30);
        Long generatedId = 123L; // Simulating the generated ID
        Items registeredItems = new Items(generatedId, itemsDTO.name(), itemsDTO.price());
        when(itemsService.registerItems(itemsDTO)).thenReturn(registeredItems);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/");

        // Call the method to be tested
        ResponseEntity<Items> responseEntity = itemsController.registerItems(itemsDTO, uriBuilder);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Check that the location header contains the expected URI with the generated ID
        assertEquals("/items", responseEntity.getHeaders().getLocation().getPath());

        // Verify that the service method was called with the correct argument
        verify(itemsService).registerItems(itemsDTO);
    }

    @Test
    void testUpdateItems() {
        // Mock data
        long itemId = 123L;
        ItemsDTO itemsDTO = new ItemsDTO("Updated Items", 200.0);

        // Call the method to be tested
        ResponseEntity<String> responseEntity = itemsController.updateItems(itemsDTO, itemId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Items updated successfully", responseEntity.getBody());

        // Verify that the service method was called with the correct arguments
        verify(itemsService).updateItems(itemId, itemsDTO);
    }

    @Test
    void testGetItemsByName() {
        // Mock data
        String itemName = "Test Item";
        Items item1 = new Items(1L, itemName, 100.0);
        Items item2 = new Items(2L, itemName, 150.0);
        List<Items> itemsList = Arrays.asList(item1, item2);

        // Mock service method
        when(itemsService.getItemsByName(itemName)).thenReturn(itemsList);

        // Call the method to be tested
        ResponseEntity<List<Items>> responseEntity = itemsController.getItemsByName(itemName);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(itemsList, responseEntity.getBody());

        // Verify that the service method was called with the correct argument
        verify(itemsService).getItemsByName(itemName);
    }

    @Test
    void testDeleteItems() {
        // Mock data
        Long itemId = 123L;

        // Call the method to be tested
        ResponseEntity<Items> responseEntity = itemsController.deleteItems(itemId);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called with the correct argument
        verify(itemsService).deleteItems(itemId);
    }
}