package com.github.tcsizmadia.dognames.services;

import com.github.tcsizmadia.dognames.dto.Breed;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DogBreedServiceImpl implements DogBreedService {

    private static final Logger logger = LoggerFactory.getLogger(DogBreedServiceImpl.class);

    private static final List<Breed> defaultBreeds = Collections.unmodifiableList(List.of(
            new Breed(1, "Affenpinscher", "The Affenpinscher is a terrier-like toy breed of dog."),
            new Breed(2, "Afghan Hound", "The Afghan Hound is an aristocrat, his whole appearance one of dignity and aloofness with no trace of plainness or coarseness."),
            new Breed(3, "Airedale Terrier", "The Airedale Terrier is the largest of all terrier breeds."),
            new Breed(4, "Akita", "The Akita is a large and powerful dog with a noble and intimidating presence."),
            new Breed(5, "Alaskan Malamute", "The Alaskan Malamute stands well over the pads, and this stance gives the appearance of much activity and a proud carriage, with head erect and eyes alert showing interest and curiosity.")
        )
    );

    List<Breed> breeds;

    @PostConstruct
    public void init() {
        var filePath = Path.of("src/main/resources/dog-breeds.csv");
        if (Files.exists(filePath)) {
            breeds = readBreedsFromFile(filePath);
        } else {
            breeds = defaultBreeds;
        }
    }

    private Optional<Breed> lineToBreed(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String description = parts[2].replace("\"", "");
            return Optional.of(new Breed(id, name, description));
        }
        return Optional.empty();
    }

    private List<Breed> readBreedsFromFile(Path filePath) {
        List<Breed> fetchedBreeds = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                this.lineToBreed(line).ifPresent(fetchedBreeds::add);
            }
        } catch (IOException e) {
            logger.error("Error reading breeds from file", e);
        }
        return fetchedBreeds;
    }

    @Override
    public List<Breed> getBreeds() {
        return this.breeds;
    }
}
