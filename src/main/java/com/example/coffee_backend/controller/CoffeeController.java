package com.example.coffee_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.example.coffee_backend.entity.CoffeeEntity;
import com.example.coffee_backend.service.CoffeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService){
        this.coffeeService = coffeeService;
    }



    @PostMapping("/")
    public ResponseEntity<CoffeeEntity>createCoffeeEntry(@RequestBody CoffeeEntity coffeeEntity) {
        CoffeeEntity createdCoffeeEntity = coffeeService.createCoffee(coffeeEntity);
        return new ResponseEntity<>(createdCoffeeEntity, HttpStatus.CREATED);
        
    }

    @PutMapping("update/{name}")
    public ResponseEntity<Optional<CoffeeEntity>> updateCoffee(@PathVariable String name, @RequestBody CoffeeEntity coffeeEntity) {
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

    @GetMapping("/search/{name}")
    public ResponseEntity<CoffeeEntity> getCoffeeByName(@PathVariable String name) {
        return coffeeService.findByName(name)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

}
