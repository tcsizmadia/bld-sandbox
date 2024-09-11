package com.github.tcsizmadia.dognames.services;

import java.util.Collection;
import java.util.List;

/**
 * Service to get dog names.
 * <p>
 *     This service provides a list of dog names.
 *     The list is not exhaustive, but it is a good starting point.
 *
 * @since 0.1.0
 * @author Tamas Csizmadia
 */
public interface DogNameService {
    /**
     * Get a list of dog names.
     *
     * @return a Collection of dog names
     */
    Collection<String> getNames();
}
