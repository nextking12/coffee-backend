package com.example.coffee_backend.repository;

import com.example.coffee_backend.model.CoffeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {

}
