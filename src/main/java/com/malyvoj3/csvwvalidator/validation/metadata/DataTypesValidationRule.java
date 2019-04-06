package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.DataTypeFactory;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.DataTypeDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.FormatDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.InheritanceDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.AtomicProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.domain.model.DataType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.*;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class DataTypesValidationRule extends TableDescriptionValidationRule {

    private static final String BOOLEAN_FORMAT_PATTERN = "\\w+\\|\\w+";
    private static final String DEFAULT_NUMERIC_FORMAT_PATTERN = "[0\\#\\.\\,E\\+\\%\\u2030\\-]";

    private final DataTypeFactory dataTypeFactory;

    @Override
    public List<? extends ValidationError> validate(TableDescription table) {
        List<ValidationError> errors = new ArrayList<>();
        List<InheritanceDescription> inheritanceDescriptions = getInheritanceDescriptions(table);
        for (InheritanceDescription desc : inheritanceDescriptions) {
            if (desc.getDataType() != null && desc.getDataType().getValue() != null) {
                DataTypeDescription dataType = desc.getDataType().getValue();
                errors.addAll(validateFormat(dataType));
                errors.addAll(validateLengthConstraints(dataType));
                errors.addAll(validateValueConstraints(dataType));
            }
        }
        return errors;
    }

    private List<? extends ValidationError> validateFormat(DataTypeDescription desc) {
        List<ValidationError> validationErrors = new ArrayList<>();
        String base = desc.getBase() != null ? desc.getBase().getValue() : null;
        FormatDescription formatDescription = Optional.of(desc)
                .map(DataTypeDescription::getFormat)
                .map(AtomicProperty::getValue)
                .orElse(null);
        String format = Optional.ofNullable(formatDescription)
                .map(FormatDescription::getPattern)
                .map(StringAtomicProperty::getValue)
                .orElse(null);
        base = base != null ? base : CsvwKeywords.STRING_DATA_TYPE;
        DataTypeGroup group = DataTypeDefinition.getByName(base).getGroup();

        if (StringUtils.isNotEmpty(format)) {
            switch (group) {
                case NUMERIC:
                    String groupChar = Optional.of(formatDescription)
                            .map(FormatDescription::getGroupChar)
                            .map(StringAtomicProperty::getValue)
                            .orElse(null);
                    String decimalChar = Optional.of(formatDescription)
                            .map(FormatDescription::getDecimalChar)
                            .map(StringAtomicProperty::getValue)
                            .orElse(null);
                    String numericPatter = createNumericPattern(groupChar, decimalChar);
                    if (!matchPattern(format, numericPatter)) {
                        validationErrors.add(ValidationError.warn("Property 'format' is invalid NUMERIC format."));
                        desc.getFormat().getValue().setPattern(null);
                    }
                    break;
                case BOOLEAN:
                    if (!matchPattern(format, BOOLEAN_FORMAT_PATTERN)) {
                        validationErrors.add(ValidationError.warn("Property 'format' is invalid BOOLEAN format."));
                        desc.getFormat().getValue().setPattern(null);
                    }
                    break;
                case DATE:
                case DURATION:
                default:
                    // TODO
                    break;
            }
        }
        return validationErrors;
    }

    private String createNumericPattern(String groupChar, String decimalChar) {
        String pattern;
        if (groupChar != null && decimalChar != null) {
            pattern = DEFAULT_NUMERIC_FORMAT_PATTERN + "|" + String.format("[\\%s\\%s]", groupChar, decimalChar);
        } else if (groupChar != null) {
            pattern = DEFAULT_NUMERIC_FORMAT_PATTERN + "|" + String.format("\\%s", groupChar);
        } else if (decimalChar != null) {
            pattern = DEFAULT_NUMERIC_FORMAT_PATTERN + "|" + String.format("\\%s", decimalChar);
        } else {
            pattern = DEFAULT_NUMERIC_FORMAT_PATTERN;
        }
        pattern = String.format("(%s)+", pattern);
        return pattern;
    }

    protected boolean matchPattern(String string, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private List<? extends ValidationError> validateValueConstraints(DataTypeDescription desc) {
        List<ValidationError> validationErrors = new ArrayList<>();
        String minimum = desc.getMinimum() != null ? desc.getMinimum().getValue() : null;
        String maximum = desc.getMaximum() != null ? desc.getMaximum().getValue() : null;
        String minInclusive = desc.getMinInclusive() != null ? desc.getMinInclusive().getValue() : null;
        String maxInclusive = desc.getMaxInclusive() != null ? desc.getMaxInclusive().getValue() : null;
        String minExclusive = desc.getMinExclusive() != null ? desc.getMinExclusive().getValue() : null;
        String maxExclusive = desc.getMaxExclusive() != null ? desc.getMaxExclusive().getValue() : null;
        String base = desc.getBase() != null ? desc.getBase().getValue() : null;
        base = base != null ? base : CsvwKeywords.STRING_DATA_TYPE;

        if (DataTypeConstraintGroup.VALUE == DataTypeDefinition.getByName(base).getConstraintGroup()) {
            // Validate logic without datatypes.
            if (minimum != null && minInclusive != null && !minimum.equals(minInclusive)) {
                validationErrors.add(ValidationError.error("Datatype has 'minimum' and 'minInclusive' properties with different values."));
            }
            if (maximum != null && maxInclusive != null && !maximum.equals(maxInclusive)) {
                validationErrors.add(ValidationError.error("Datatype has 'maximum' and 'maxInclusive' properties with different values."));
            }
            if (minInclusive != null && minExclusive != null) {
                validationErrors.add(ValidationError.error("Datatype has specified both 'minInclusive' and 'minExclusive' properties."));
            }
            if (maxInclusive != null && maxExclusive != null) {
                validationErrors.add(ValidationError.error("Datatype has specified both 'maxInclusive' and 'maxExclusive' properties."));
            }

            DataType dataType = DataType.builder()
                    .base(desc.getBase() != null ? desc.getBase().getValue() : null)
                    .build();
            if (dataType.getBase() == null) {
                dataType.setBase(CsvwKeywords.STRING_DATA_TYPE);
            }
            if (minInclusive == null) {
                minInclusive = minimum;
            }
            if (maxInclusive == null) {
                maxInclusive = maximum;
            }

            // Create datatypes for constraints.
            ValueType minInclusiveValue = null;
            if (minInclusive != null) {
                try {
                    minInclusiveValue = dataTypeFactory.createDataType(minInclusive, dataType);
                } catch (DataTypeFormatException ex) {
                    String errorMsg = String.format("Property 'minInclusive' doesn't have valid format for datatype '%s'.", dataType.getBase());
                    validationErrors.add(ValidationError.error(errorMsg));
                }
            }
            ValueType maxInclusiveValue = null;
            if (maxInclusive != null) {
                try {
                    maxInclusiveValue = dataTypeFactory.createDataType(maxInclusive, dataType);
                } catch (DataTypeFormatException ex) {
                    String errorMsg = String.format("Property 'maxInclusive' doesn't have valid format for datatype '%s'.", dataType.getBase());
                    validationErrors.add(ValidationError.error(errorMsg));
                }
            }
            ValueType minExclusiveValue = null;
            if (minExclusive != null) {
                try {
                    minExclusiveValue = dataTypeFactory.createDataType(minExclusive, dataType);
                } catch (DataTypeFormatException ex) {
                    String errorMsg = String.format("Property 'minExclusive' doesn't have valid format for datatype '%s'.", dataType.getBase());
                    validationErrors.add(ValidationError.error(errorMsg));
                }
            }
            ValueType maxExclusiveValue = null;
            if (maxExclusive != null) {
                try {
                    maxExclusiveValue = dataTypeFactory.createDataType(maxExclusive, dataType);
                } catch (DataTypeFormatException ex) {
                    String errorMsg = String.format("Property 'maxExclusive' doesn't have valid format for datatype '%s'.", dataType.getBase());
                    validationErrors.add(ValidationError.error(errorMsg));
                }
            }

            try {
                if (minInclusiveValue != null && maxInclusiveValue != null && maxInclusiveValue.isLower(minInclusiveValue)) {
                    String errorMsg = "Property 'maxInclusive' is less than property 'minInclusive'.";
                    validationErrors.add(ValidationError.error(errorMsg));
                }
                if (minInclusiveValue != null && maxExclusiveValue != null && maxExclusiveValue.isLowerEq(minInclusiveValue)) {
                    String errorMsg = "Property 'maxExclusive' is less than or equal to property 'minInclusive'.";
                    validationErrors.add(ValidationError.error(errorMsg));
                }
                if (minExclusiveValue != null && maxExclusiveValue != null && maxExclusiveValue.isLower(minExclusiveValue)) {
                    String errorMsg = "Property 'maxExclusive' is less than or equal to property 'minExclusive'.";
                    validationErrors.add(ValidationError.error(errorMsg));
                }
                if (minExclusiveValue != null && maxInclusiveValue != null && maxInclusiveValue.isLowerEq(minExclusiveValue)) {
                    String errorMsg = "Property 'maxInclusive' is less than or equal to property 'minExclusive'.";
                    validationErrors.add(ValidationError.error(errorMsg));
                }
            } catch (IncomparableDataTypeException e) {
                log.error("Problem during comparing.");
                throw new RuntimeException();
            }
        } else {
            if (minimum != null) {
                validationErrors.add(ValidationError.error("Not numeric, date/time or duration datatype has defined 'minimum' property."));
            }
            if (minInclusive != null) {
                validationErrors.add(ValidationError.error("Not numeric, date/time or duration datatype has defined 'minInclusive' property."));
            }
            if (minExclusive != null) {
                validationErrors.add(ValidationError.error("Not numeric, date/time or duration datatype has defined 'minExclusive' property."));
            }
            if (maximum != null) {
                validationErrors.add(ValidationError.error("Not numeric, date/time or duration datatype has defined 'maximum' property."));
            }
            if (maxInclusive != null) {
                validationErrors.add(ValidationError.error("Not numeric, date/time or duration datatype has defined 'maxInclusive' property."));
            }
            if (maxExclusive != null) {
                validationErrors.add(ValidationError.error("Not numeric, date/time or duration datatype has defined 'maxExclusive' property."));
            }
        }

        return validationErrors;
    }

    private List<? extends ValidationError> validateLengthConstraints(DataTypeDescription desc) {
        List<ValidationError> validationErrors = new ArrayList<>();
        Long length = desc.getLength() != null ? desc.getLength().getValue() : null;
        Long minLength = desc.getMinLength() != null ? desc.getMinLength().getValue() : null;
        Long maxLength = desc.getMaxLength() != null ? desc.getMaxLength().getValue() : null;
        String base = desc.getBase() != null ? desc.getBase().getValue() : null;
        base = base != null ? base : CsvwKeywords.STRING_DATA_TYPE;
        if (DataTypeConstraintGroup.LENGTH == DataTypeDefinition.getByName(base).getConstraintGroup()) {
            if (length != null && minLength != null && length < minLength) {
                validationErrors.add(ValidationError.error("Datatype has 'length' property lower than 'minLength'."));
            }
            if (length != null && maxLength != null && length > maxLength) {
                validationErrors.add(ValidationError.error("Datatype has 'length' property greater than 'maxLength'"));
            }
            if (minLength != null && maxLength != null && minLength > maxLength) {
                validationErrors.add(ValidationError.error("Datatype has 'minLength' property greater than 'maxLength'"));
            }
        } else {
            if (length != null) {
                validationErrors.add(ValidationError.error("Not string or binary datatype has defined 'length' property."));
            }
            if (minLength != null) {
                validationErrors.add(ValidationError.error("Not string or binary  datatype has defined 'minLength' property."));
            }
            if (maxLength != null) {
                validationErrors.add(ValidationError.error("Not string or binary  datatype has defined 'maxLength' property."));
            }
        }
        return validationErrors;
    }

}
