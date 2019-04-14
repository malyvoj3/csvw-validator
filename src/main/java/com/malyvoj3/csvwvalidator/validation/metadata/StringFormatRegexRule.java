package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringFormatRegexRule extends TableDescriptionValidationRule {

    @Override
    public List<? extends ValidationError> validate(TableDescription table) {
        List<ValidationError> errors = new ArrayList<>();
        List<InheritanceDescription> inheritanceDescriptions = getInheritanceDescriptions(table);
        for (InheritanceDescription desc : inheritanceDescriptions) {
            if (desc.getDataType() != null && desc.getDataType().getValue() != null) {
                DataTypeDescription dataTypeDesc = desc.getDataType().getValue();
                if (isFormatRegex(dataTypeDesc)) {
                    String pattern = dataTypeDesc.getFormat().getValue().getPattern().getValue();
                    try {
                        Pattern.compile(pattern);
                    } catch (Exception ex) {
                        dataTypeDesc.getFormat().getValue().setPattern(null);
                        errors.add(ValidationError.warn("Datatype has incorrect regexp in format property."));
                    }
                }
            }
        }
        return errors;
    }

    private boolean isFormatRegex(DataTypeDescription desc) {
        return desc.getBase() != null
                && desc.getFormat() != null
                && desc.getFormat().getValue() != null
                && desc.getFormat().getValue().getPattern() != null
                && desc.getFormat().getValue().getPattern().getValue() != null
                && CsvwKeywords.STRING_DATA_TYPE.equals(desc.getBase().getValue()); // TODO ENUM a ty co nejsou NUMERIC atd.
    }

}
