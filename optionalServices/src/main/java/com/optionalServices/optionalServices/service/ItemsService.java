package com.optionalServices.optionalServices.service;

import com.optionalServices.optionalServices.dto.ItemsDTO;
import com.optionalServices.optionalServices.entity.items.Items;
import com.optionalServices.optionalServices.repository.items.ItemsRepository;
import com.optionalServices.optionalServices.utils.Pagination;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {

    private final ItemsRepository repository;

    public Pagination<Items> getAllItems(Integer limit, Integer offset) {
        var pageRequest = PageRequest.of(offset, limit);
        var itemsPagination = repository.findAll(pageRequest);
        return new Pagination<>(itemsPagination);
    }

    public ItemsService(ItemsRepository repository) {
        this.repository = repository;
    }

    public Optional<Items> getItemsById(long id) {
        return repository.findById(id);
    }

    public Items registerItems(ItemsDTO itemsDTO) {
        return repository.save(new Items(itemsDTO));
    }

    public List<Items> getItemsByName(String name) {
        return repository.findByName(name);

    }

    public void deleteItems(Long id) {
        var items = getItemsById(id);
        items.ifPresent(it ->
                repository.deleteById(it.getId())
        );
    }

    @Transactional
    public void updateItems(Long id, ItemsDTO itemsDTO) {
        Optional<Items> optionalItems = repository.findById(id);
        if (optionalItems.isPresent()) {
            Items items = (Items) optionalItems.get();
            items.update(itemsDTO);
            //return repository.save(new Items(itemsDTO));
        } else {
            throw new EntityNotFoundException("Item with id " + id + " not found");
        }
    }
}