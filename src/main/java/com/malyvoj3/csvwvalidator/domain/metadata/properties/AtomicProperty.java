package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
// TODO mozna dat pryc z Property normalize
public abstract class AtomicProperty<T> extends Property<T> {

    public AtomicProperty(T value) {
        super(value);
    }

}
