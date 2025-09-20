package com.example.coffee_backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.coffee_backend.entity.CoffeeEntity;
import com.example.coffee_backend.service.CoffeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coffees")
@Validated
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService){
        this.coffeeService = coffeeService;
    }


    @PostMapping("/")
    public ResponseEntity<CoffeeEntity> createCoffeeEntry(@Valid @RequestBody CoffeeEntity coffeeEntity) {
        CoffeeEntity createdCoffeeEntity = coffeeService.createCoffee(coffeeEntity);
        return new ResponseEntity<>(createdCoffeeEntity, HttpStatus.CREATED);

    }

    @PutMapping("update/name/{name}")
    public ResponseEntity<Optional<CoffeeEntity>> updateCoffee(
            @PathVariable
            @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", message = "Name contains invalid characters")
            @Size (min = 1, max = 50, message = "Name must be between 1 and 50 characters") 
            String name, 
            @Valid @RequestBody CoffeeEntity coffeeEntity) {
        try {
            Optional<CoffeeEntity> updatedCoffeeEntity = coffeeService.updateCoffee(name, coffeeEntity);
            return new ResponseEntity<>(updatedCoffeeEntity, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    @GetMapping("/")
    public List<CoffeeEntity> getAllCoffees() {
        return coffeeService.findAll();
    }

    @GetMapping("/search/name/{name}")
     public ResponseEntity<CoffeeEntity> getCoffeeByName(
            @PathVariable
            @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", message = "Name contains invalid characters")
            @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
            String name) {
        return coffeeService.findByName(name)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/search/origin/{origin}")
    public ResponseEntity<List<CoffeeEntity>> getCoffeeByOrigin(
            @PathVariable
            @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", message = "Origin contains invalid characters")
            @Size(min = 1, max = 50, message = "Origin must be between 1 and 50 characters")
            String origin) {
        List<CoffeeEntity> coffees = coffeeService.findAllByOrigin(origin);
        return ResponseEntity.ok(coffees);
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<CoffeeEntity>> getCoffeeByType(
            @PathVariable
            @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", message = "Type contains invalid characters")
            @Size(min = 1, max = 50, message = "Type must be between 1 and 50 characters")
            String type) {
        List<CoffeeEntity> coffees = coffeeService.findAllByType(type);
        return ResponseEntity.ok(coffees);
    }

    @DeleteMapping("/delete/name/{name}")
    public ResponseEntity<Void> deleteCoffee(
            @PathVariable
            @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", message = "Name contains invalid characters")
            @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
            String name) {
        coffeeService.deleteCoffee(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

