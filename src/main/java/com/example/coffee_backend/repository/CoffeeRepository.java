package com.example.coffee_backend.repository;

import com.example.coffee_backend.entity.CoffeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {
    Optional<CoffeeEntity> findByName(String name);

    Optional<CoffeeEntity> findByOrigin(String origin);

    Optional<CoffeeEntity> findByType(String type);

    List<CoffeeEntity> findAllByType(String type);

    List<CoffeeEntity> findAllByOrigin(String origin);



}
