package com.malyvoj3.csvwvalidator.metadata.domain;

public interface CompatibleDescription<T> {

    boolean isCompatibleWith(T other);

}
