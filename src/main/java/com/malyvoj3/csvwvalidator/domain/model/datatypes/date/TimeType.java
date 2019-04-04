package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeType extends ValueType {

    private XMLGregorianCalendar value;
    private String timePattern;
    private LocalTime time;
    private int zoneMinuteOffset;

    public TimeType(String stringValue, String timePattern) throws DataTypeFormatException {
        super(stringValue);
        this.timePattern = timePattern;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern);
            TemporalAccessor temporalAccessor = formatter.parse(stringValue);
            if (temporalAccessor.query(TemporalQueries.zone()) == null) {
                temporalAccessor = formatter.withZone(ZoneId.systemDefault()).parse(stringValue);
            }
            time = LocalTime.from(formatter.parse(stringValue));
            ZoneOffset offsetSeconds = temporalAccessor.query(TemporalQueries.offset());
            BigDecimal fractionalSeconds = time.getNano() > 0 ? new BigDecimal(time.getNano()).movePointLeft(9) : null;
            zoneMinuteOffset = offsetSeconds != null ? offsetSeconds.getTotalSeconds() / 60 : 0;
            value = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    null, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
                    time.getHour(), time.getMinute(), time.getSecond(),
                    fractionalSeconds, zoneMinuteOffset);
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
    public String getCanonicalForm() {
        return value.normalize().toString();
    }

    @Override
    public int compareTo(@NonNull ValueType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        TimeType that = (TimeType) other;
        return value.compare(that.getValue());
    }

}
