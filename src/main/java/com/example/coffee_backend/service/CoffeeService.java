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

    public CoffeeEntity updateCoffee(String name, CoffeeEntity coffeeEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCoffee'");
    }

    /*
     * if (!coffeeRepository.existsByName(coffeeEntity.getName())) {
     * throw new RuntimeException("Coffee with name '" + coffeeEntity.getName() +
     * "' doesn't exist");
     * }
     * return coffeeRepository.findByName(name)
     * .map(coffee -> {
     * coffee.setName(updatedCoffee.getName());
     * coffee.setOrigin(updatedCoffee.getOrigin());
     * coffee.setType(updatedCoffee.getType());
     * coffee.setGrindSize(updatedCoffee.getGrindSize());
     * coffee.setWeightInGrams(updatedCoffee.getWeightInGrams());
     * return coffeeRepository.save(coffee);
     * });
     */

}
