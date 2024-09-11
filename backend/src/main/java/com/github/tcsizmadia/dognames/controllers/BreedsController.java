package com.github.tcsizmadia.dognames.controllers;

import com.github.tcsizmadia.dognames.dto.Breed;
import com.github.tcsizmadia.dognames.services.DogBreedService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dogs/breeds")
public class BreedsController {

    private final DogBreedService dogBreedService;

    public BreedsController(DogBreedService dogBreedService) {
        this.dogBreedService = dogBreedService;
    }

    @RequestMapping
    public List<Breed> getBreeds() {
        return dogBreedService.getBreeds();
    }
}
