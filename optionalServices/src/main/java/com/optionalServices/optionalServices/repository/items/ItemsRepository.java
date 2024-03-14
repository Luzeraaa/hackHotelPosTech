package com.optionalServices.optionalServices.repository.items;

import com.optionalServices.optionalServices.entity.items.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    List<Items> findByName(String name);
}
