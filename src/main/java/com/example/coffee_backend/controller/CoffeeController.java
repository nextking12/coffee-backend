package com.example.coffee_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffee_backend.entity.CoffeeEntity;
import com.example.coffee_backend.service.CoffeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService){
        this.coffeeService = coffeeService;
    }



    @PostMapping("/coffees")
    public ResponseEntity<CoffeeEntity>createCoffeeEntry(@RequestBody CoffeeEntity coffeeEntity) {
        CoffeeEntity createdCoffeeEntity = coffeeService.createCoffee(coffeeEntity);
        return new ResponseEntity<>(createdCoffeeEntity, HttpStatus.CREATED);
        
    }
    
}
