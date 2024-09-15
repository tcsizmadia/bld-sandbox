package com.github.tcsizmadia.dognames.breedgenerator.extension;

import rife.bld.CommandHelp;

public class BreedGeneratorHelp implements CommandHelp {
    /**
     * Returns a short description about the command.
     *
     * @return the short summary, defaults to {@code ""}
     * @since 1.5
     */
    @Override
    public String getSummary() {
        return "Using a locally running instance of Ollama, this command generates a list of dog breeds.";
    }

    /**
     * Returns the full help description of a command.
     *
     * @param topic
     * @return the full help description, defaults to {@code ""}
     * @since 1.5
     */
    @Override
    public String getDescription(String topic) {
        return """
                This command generates a list of dog breeds using a locally running instance of Ollama.
                The list is saved to the file dog-breeds.csv in the resources directory.
                """;
    }

}
