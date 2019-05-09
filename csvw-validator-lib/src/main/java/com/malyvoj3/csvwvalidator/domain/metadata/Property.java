package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for CSV on the Web properties.
 * @param <T>
 */
@Data
public abstract class Property<T> implements Normalizable {

    protected T value;

    public Property(T value) {
        this.value = value;
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        return new ArrayList<>();
    }
}
