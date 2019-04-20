package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListType extends ValueType {

    private List<ValueType> elements = new ArrayList<>();

    public ListType(String stringValue) {
        super(stringValue);
    }

    public void add(ValueType element) {
        elements.add(element);
    }

    @Override
    public boolean isLengthDataType() {
        return false;
    }

    @Override
    public boolean isValueDataType() {
        return false;
    }

}
