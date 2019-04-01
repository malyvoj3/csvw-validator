package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

@Data
@EqualsAndHashCode(callSuper = true)
public class DateTimeType extends DataType {

    private String dateTimePattern;
    private ZonedDateTime value;

    public DateTimeType(String stringValue, String dateTimePattern) throws DataTypeFormatException {
        super(stringValue);
        this.dateTimePattern = dateTimePattern;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern.replace("T", "'T'"));
            TemporalAccessor temporalAccessor = formatter.parse(stringValue);
            if (temporalAccessor.query(TemporalQueries.zone()) == null) {
                temporalAccessor = formatter.withZone(ZoneId.systemDefault()).parse(stringValue);
            }
            value = ZonedDateTime.from(temporalAccessor);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    @Override
    public boolean isLengthDataType() {
        return false;
    }

    @Override
    public boolean isValueDataType() {
        return true;
    }

    @Override
    public int compareTo(@NonNull DataType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        DateTimeType that = (DateTimeType) other;
        return value.compareTo(that.getValue());
    }

}
