package com.malyvoj3.csvwvalidator.domain.metadata;

public interface CompatibleDescription<T extends ObjectDescription> {

    boolean isCompatibleWith(T other);

}
