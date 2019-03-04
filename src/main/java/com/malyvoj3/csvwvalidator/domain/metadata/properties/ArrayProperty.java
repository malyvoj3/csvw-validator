package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import java.util.List;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.ObjectDescription;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArrayProperty<E extends ObjectDescription> extends Property<List<E>> {

    public ArrayProperty(List<E> value) {
        super(value);
    }

    @Override
    public void normalize(Context context) {
        value.forEach(element -> element.normalize(context));
    }
}
