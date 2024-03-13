package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.entity.Items.Items;
import com.optionalServices.optionalServices.dto.ItemsDTO;
import com.optionalServices.optionalServices.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public record ItemsController(ItemsService service) {

    private static final String ALL = "all";
    private static final String ITEMS_ID_PATH = "/items";
    private static final String TEN = "10";
    private static final String ZERO = "0";
    private static final String ID = "id";
    private static final String NAME= "name";
    private static final String ITEMS_MESSAGE = "Items updated successfully";

    @GetMapping(path = ALL)
    public ResponseEntity<Items> getAllItems(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset) {
        var items = service.getAllItems(limit, offset);
        return ResponseEntity.ok(items);
    }


    @GetMapping(path = {ID})
    public ResponseEntity<Items> getServicesById(@RequestParam long id){
        var services = service.getClass(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<Items> resgisterItems(@RequestBody ItemsDTO itemsDTO){
        var items = service.resgisterItems(itemsDTO);
        return ResponseEntity
                .created(uriBuilder.path(ITEMS_ID_PATH).buildAndExpand(service.getId()).toUri())
                .body(items);
    }

    @PutMapping
    public ResponseEntity<String> updateItems(
            @RequestBody ItemsDTO itemsDTO
            @RequestParam final Long id){
        service.update(id, itemsDTO);
        return ResponseEntity.ok(ITEMS_MESSAGE);
    }

    @GetMapping(params = {NAME})
    public ResponseEntity<List<Items>> getItemsByName(@RequestParam String name) {
        var items = service.getItemsByName(name);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping(params = {ID})
    public ResponseEntity<Items> deleteItems(final @RequestParam Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
}
