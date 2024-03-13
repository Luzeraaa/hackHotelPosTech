package com.optionalServices.optionalServices.repository.Items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemsRepository extends JpaRepository<ItemsRepository, Long> {
    Optional<ItemsRepository> findAllServices();
}
