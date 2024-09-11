package com.github.tcsizmadia.dognames.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class DogNameServiceImpl implements DogNameService {
    /**
     * Get a list of dog names.
     *
     * @return a list of dog names
     */
    @Override
    public Collection<String> getNames() {
        return Set.of("Buddy", "Max", "Daisy", "Lucy", "Charlie", "Molly", "Sadie", "Bailey", "Duke", "Rocky");
    }
}
