package com.example.coffee_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffee_backend.entity.CoffeeEntity;
import com.example.coffee_backend.service.CoffeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



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
    public ResponseEntity<CoffeeEntity> updateCoffee(@PathVariable String name, @RequestBody CoffeeEntity coffeeEntity) {
        try {
            CoffeeEntity updatedCoffeeEntity = coffeeService.updateCoffee(name, coffeeEntity);
            return new ResponseEntity<>(updatedCoffeeEntity, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
}
