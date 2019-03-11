package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import java.util.List;

public class ListAtomicProperty<E> extends AtomicProperty<List<E>> {

    public ListAtomicProperty(List<E> value) {
        super(value);
    }
}
