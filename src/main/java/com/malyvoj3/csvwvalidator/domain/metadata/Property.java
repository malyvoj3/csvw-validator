package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public abstract class Property<T> implements Normalizable {

    protected T value;

    public Property(T value) {
        this.value = value;
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        return Collections.emptyList();
    }
}
