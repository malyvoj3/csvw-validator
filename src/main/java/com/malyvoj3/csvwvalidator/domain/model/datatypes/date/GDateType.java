package com.malyvoj3.csvwvalidator.domain.model.datatypes.date;

import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import lombok.Data;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
public abstract class GDateType extends DataTypeDefinition {

    protected XMLGregorianCalendar value;
    protected String format;

    public GDateType(String stringValue) throws DataTypeFormatException {
        super(stringValue);
        try {
            value = DatatypeFactory.newInstance().newXMLGregorianCalendar(stringValue);
        } catch (Exception ex) {
            throw new DataTypeFormatException();
        }
    }

    public GDateType(String stringValue, String format) throws DataTypeFormatException {
        this(stringValue);
        matchPattern(stringValue, format);
    }

    @Override
    public String getCanonicalForm() {
        return value.toString();
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
