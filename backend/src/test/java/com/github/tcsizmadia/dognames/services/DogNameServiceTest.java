package com.github.tcsizmadia.dognames.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DogNameServiceTest {

    @Autowired
    private DogNameService service;

    @Test
    void testGetNamesShouldReturnNonEmptyList() {
        List<String> dogNames = new ArrayList<>(service.getNames());
        assertThat(dogNames).isNotEmpty();
    }
}