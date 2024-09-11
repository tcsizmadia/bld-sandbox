package com.github.tcsizmadia.dognames;

import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Logger;

public class BreedGenerator {

    private static final Logger logger = Logger.getLogger(BreedGenerator.class.getName());

    private static final String PROMPT = """
            Create a CSV list of Dog breeds.
            Include exactly 10 entries.
            The first line must be the header.
            The header should contain these fields: id, name, description.
            Respond only with the CSV and nothing else.
            """;

    private static final String MODEL = "llama3.1";
    public static final String OLLAMA_API_URL = "http://localhost:11434/api/generate";

    private final Path destination;

    public BreedGenerator(Path destination) {
        this.destination = destination;
        logger.info("BreedGenerator initialized");
    }

    private String createJsonRequestPayload() throws IOException {
        var requestMap = new HashMap<String, Object>(3);
        requestMap.put("model", MODEL);
        requestMap.put("prompt", PROMPT);
        requestMap.put("stream", false);

        return JSON.std.asString(requestMap);
    }

    private String extractCsvFromResponse(String response) throws IOException {
        var mapFromResponse = JSON.std.mapFrom(response);
        return mapFromResponse.get("response").toString();
    }

    private Optional<String> fetchBreeds() {
        logger.info("Fetching breeds using Ollama");

        try {
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(OLLAMA_API_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(this.createJsonRequestPayload()))
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(this.extractCsvFromResponse(response.body()));
        } catch (IOException | InterruptedException e) {
            logger.severe("Error fetching breeds: " + e.getMessage());
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }

    public void generate() {
        logger.info("Generating breeds using Ollama");

        this.fetchBreeds().ifPresent(csv -> {
            try {
                Files.writeString(destination, csv.replace("\\n", System.lineSeparator()));
            } catch (IOException e) {
                logger.severe("Error writing breeds to file: " + e.getMessage());
            }
        });
   }
}
