package com.github.tcsizmadia.dognames.controllers;

import com.github.tcsizmadia.dognames.services.DogNameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/dogs/names")
public class NamesController {

    private final DogNameService dogNameService;

    public NamesController(DogNameService dogNameService) {
        this.dogNameService = dogNameService;
    }

    @RequestMapping
    public Collection<String> getNames() {
        return dogNameService.getNames();
    }
}
