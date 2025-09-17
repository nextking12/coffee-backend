package com.example.coffee_backend.service;

import com.example.coffee_backend.entity.CoffeeEntity;
import com.example.coffee_backend.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<CoffeeEntity> findAll() {
        return coffeeRepository.findAll();
    }

    public List<CoffeeEntity> findAllByOrigin(String origin) {
        return coffeeRepository.findAllByOrigin(origin);
    }

    public List<CoffeeEntity> findAllByType(String type) {
        return coffeeRepository.findAllByType(type);
    }

    public Optional<CoffeeEntity> findById(Long id) {
        return coffeeRepository.findById(id);
    }

    public Optional<CoffeeEntity> findByName(String name) {
        return coffeeRepository.findByName(name);
    }

    public CoffeeEntity createCoffee(CoffeeEntity coffeeEntity) {
        if (coffeeRepository.existsByName(coffeeEntity.getName())) {
            throw new RuntimeException("Coffee with name '" + coffeeEntity.getName() + "' already exists");
        }
        return coffeeRepository.save(coffeeEntity);
    }

    public Optional<CoffeeEntity> updateCoffee(String name, CoffeeEntity coffeeEntity) {
        Optional<CoffeeEntity> existingCoffee = coffeeRepository.findByName(name);
        if (existingCoffee.isEmpty()) {
            throw new RuntimeException("Coffee with name '" + name + "' doesn't exist");
        }

        CoffeeEntity coffee = existingCoffee.get();
        coffee.setName(coffeeEntity.getName());
        coffee.setOrigin(coffeeEntity.getOrigin());
        coffee.setType(coffeeEntity.getType());
        coffee.setGrindSize(coffeeEntity.getGrindSize());
        coffee.setWeightInGrams(coffeeEntity.getWeightInGrams());

        return Optional.of(coffeeRepository.save(coffee));
    }

};
