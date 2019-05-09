package com.malyvoj3.csvwvalidator.domain.metadata;

/**
 * Object description which can be compared if it is compatible with another one.
 * @param <T>
 */
public interface CompatibleDescription<T extends ObjectDescription> {

    /**
     * Validates if this description object is compatible with {@code other}.
     * @param other Object description of the same type.
     * @return True if object descriptions are compatible, otherwise false.
     */
    boolean isCompatibleWith(T other);

}
