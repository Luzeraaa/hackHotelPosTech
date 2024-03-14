package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.entity.items.Items;
import com.optionalServices.optionalServices.dto.ItemsDTO;
import com.optionalServices.optionalServices.service.ItemsService;
import com.optionalServices.optionalServices.utils.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public record ItemsController(ItemsService itemsService) {

    private static final String ALL = "all";
    private static final String ITEMS_ID_PATH = "/items";
    private static final String TEN = "10";
    private static final String ZERO = "0";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ITEMS_MESSAGE = "Items updated successfully";

    @GetMapping(path = ALL)
    public ResponseEntity<Pagination<Items>> getAllItems(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset) {
        var services = itemsService.getAllItems(limit, offset);
        return ResponseEntity.ok(services);
    }


    @GetMapping
    public ResponseEntity<Optional<Items>> getItemsById(@RequestParam long id) {
        var services = itemsService.getItemsById(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<Items> registerItems(
            @RequestBody
            ItemsDTO itemsDTO,
            UriComponentsBuilder uriBuilder) {
        var facility = itemsService.registerItems(itemsDTO);
        return ResponseEntity
                .created(uriBuilder.path(ITEMS_ID_PATH).buildAndExpand(facility.getId()).toUri())
                .body(facility);
    }

    @PutMapping(params = {ID})
    public ResponseEntity<String> updateItems(
            @RequestBody
            ItemsDTO itemsDTO,
            @RequestParam final Long id) {
        itemsService.updateItems(id, itemsDTO);
        return ResponseEntity.ok(ITEMS_MESSAGE);
    }

    @GetMapping(params = {NAME})
    public ResponseEntity<List<Items>> getItemsByName(@RequestParam String name) {
        var items = itemsService.getItemsByName(name);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping(params = {ID})
    public ResponseEntity<Items> deleteItems(final @RequestParam Long id) {
        itemsService.deleteItems(id);

        return ResponseEntity.noContent().build();
    }
}