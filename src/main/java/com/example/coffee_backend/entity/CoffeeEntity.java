package com.example.coffee_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "espresso_stats")
public class CoffeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Coffee name is required")
    @Size(max = 50, message = "Coffee name must be less than 50 characters")
    private String name;

    @NotBlank(message = "Coffee type is required")
    @Size(max = 50, message = "Coffee type must be less than 50 characters")
    private String type;

    @NotBlank(message = "Coffee origin is required")
    @Size(max = 50, message = "Coffee origin must be less than 50 characters")
    private String origin;

    @Min(value = 1, message = "Grind size must be at least 1")
    @Max(value = 10, message = "Grind size must be at most 10")
    private int grindSize;

    @Positive(message = "Weight must be positive")
    private Float weightInGrams;

    // Constructors
    public CoffeeEntity() {}

    public CoffeeEntity(String name, String type, String origin, int grindSize, Float weightInGrams) {
        this.name = name;
        this.type = type;
        this.origin = origin;
        this.grindSize = grindSize;
        this.weightInGrams = weightInGrams;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public int getGrindSize() { return grindSize; }
    public void setGrindSize(int grindSize) { this.grindSize = grindSize; }

    public Float getWeightInGrams() { return weightInGrams; }
    public void setWeightInGrams(Float weightInGrams) { this.weightInGrams = weightInGrams; }

    @Override
    public String toString() {
        return "CoffeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", origin='" + origin + '\'' +
                ", grindSize='" + grindSize + '\'' +
                ", weightInGrams=" + weightInGrams +
                '}';
    }
}