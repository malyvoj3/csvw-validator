package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

@Data
@EqualsAndHashCode(callSuper = true)
public class DateType extends DataType {

    private XMLGregorianCalendar value;
    private String datePattern;
    private LocalDate localDate;
    private int zoneMinuteOffset;

    public DateType(String stringValue, String datePattern) throws DataTypeFormatException {
        super(stringValue);
        this.datePattern = datePattern;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            TemporalAccessor temporalAccessor = formatter.parse(stringValue);
            localDate = LocalDate.from(formatter.parse(stringValue));
            ZoneOffset zoneOffset = temporalAccessor.query(TemporalQueries.offset());

            zoneMinuteOffset = zoneOffset != null ? zoneOffset.getTotalSeconds() / 60 : 0;
            value = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    BigInteger.valueOf(localDate.getYear()), localDate.getMonthValue(), localDate.getDayOfMonth(),
                    DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
                    null, zoneMinuteOffset);
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
    public int compareTo(@NonNull DataType other) throws IncomparableDataTypeException {
        if (other == null || getClass() != other.getClass()) {
            throw new IncomparableDataTypeException();
        }
        DateType that = (DateType) other;
        return value.compare(that.getValue());
    }
}
