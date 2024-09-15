package com.github.tcsizmadia.dognames.breedgenerator.extension;


import rife.bld.BaseProject;
import rife.bld.operations.AbstractOperation;
import rife.bld.operations.exceptions.ExitStatusException;

import java.nio.file.Path;

/**
 * This class is responsible for generating a list of dog breeds using a locally running instance of Ollama.
 * The list is saved to the file dog-breeds.csv in the resources directory.
 * <p>
 * The class is an extension of the {@link AbstractOperation} class.
 * It uses the {@link BreedGenerator} class to generate the list of dog breeds.
 * </p>
 */
public class BreedGeneratorOperation extends AbstractOperation<BreedGeneratorOperation> {
    private BaseProject project;
    private String model = "llama3.1";
    private String ollamaHost = "localhost";
    private int ollamaPort = 11434;

    public BreedGeneratorOperation fromProject(BaseProject project) {
        this.project = project;
        return this;
    }

    public BreedGeneratorOperation model(String model) {
        this.model = model;
        return this;
    }

    public BreedGeneratorOperation ollamaHost(String ollamaHost) {
        this.ollamaHost = ollamaHost;
        return this;
    }

    public BreedGeneratorOperation ollamaPort(int ollamaPort) {
        this.ollamaPort = ollamaPort;
        return this;
    }

    /**
     * Performs the operation execution that can be wrapped by the {@code #executeOnce} call.
     *
     * @throws Exception when an exception occurs during the execution
     * @since 1.5.10
     */
    @Override
    public void execute() throws Exception {
        if (null == this.project) {
            throw new ExitStatusException(ExitStatusException.EXIT_FAILURE);
        }

        var destination = Path.of(this.project.srcMainResourcesDirectory().getAbsolutePath(), "dog-breeds.csv");
        var generator = new BreedGenerator(destination, this.model, String.format("http://%s:%d/api/generate", this.ollamaHost, this.ollamaPort));
        var result = generator.generate();

        this.project.exitStatus(result ? ExitStatusException.EXIT_SUCCESS : ExitStatusException.EXIT_FAILURE);
    }
}
