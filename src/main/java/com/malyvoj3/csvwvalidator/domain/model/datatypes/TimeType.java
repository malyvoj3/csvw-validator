package com.malyvoj3.csvwvalidator.domain.model.datatypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeType extends DataType {

    private String timePattern;
    private LocalTime time;
    // Zone offset in seconds
    private int zoneOffset;

    public TimeType(String stringValue, String timePattern) throws DataTypeFormatException {
        super(stringValue);
        this.timePattern = timePattern;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern);
            TemporalAccessor temporalAccessor = formatter.parse(stringValue);
            time = LocalTime.from(formatter.parse(stringValue));
            ZoneOffset offsetSeconds = temporalAccessor.query(TemporalQueries.offset());
            zoneOffset = offsetSeconds != null ? offsetSeconds.getTotalSeconds() : 0;
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    public int getSeconds() {
        return time.toSecondOfDay() + zoneOffset;
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
        TimeType that = (TimeType) other;
        return getSeconds() - that.getSeconds();
    }

}
