package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

@Data
@EqualsAndHashCode(callSuper = true)
public class DateTimeType extends DataType {

    private XMLGregorianCalendar value;
    private String dateTimePattern;
    private ZonedDateTime zonedDateTime;

    public DateTimeType(String stringValue, String dateTimePattern) throws DataTypeFormatException {
        super(stringValue);
        this.dateTimePattern = dateTimePattern;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern.replace("T", "'T'"));
            TemporalAccessor temporalAccessor = formatter.parse(stringValue);
            if (temporalAccessor.query(TemporalQueries.zone()) == null) {
                temporalAccessor = formatter.withZone(ZoneId.systemDefault()).parse(stringValue);
            }
            zonedDateTime = ZonedDateTime.from(temporalAccessor);
            int zoneMinuteOffset = zonedDateTime.getOffset().getTotalSeconds() / 60;
            value = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    BigInteger.valueOf(zonedDateTime.getYear()), zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth(),
                    zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond(),
                    new BigDecimal(zonedDateTime.getNano()).movePointLeft(9), zoneMinuteOffset);
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
        DateTimeType that = (DateTimeType) other;
        return value.compare(that.getValue());
    }

}
