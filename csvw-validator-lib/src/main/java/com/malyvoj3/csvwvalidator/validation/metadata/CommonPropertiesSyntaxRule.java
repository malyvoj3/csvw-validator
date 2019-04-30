package com.malyvoj3.csvwvalidator.validation.metadata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.CommonProperty;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.LanguageUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonPropertiesSyntaxRule extends TableDescriptionValidationRule {

    private static final String BLANK_NODE_PREFIX = "_";
    private static final String SPECIAL_NODE_PREFIX = "@";
    private static final Set<String> possibleSpecialKeys = Stream.of(
            CsvwKeywords.VALUE_PROPERTY,
            CsvwKeywords.TYPE_PROPERTY,
            CsvwKeywords.ID_PROPERTY,
            CsvwKeywords.LANGUAGE_PROPERTY
    ).collect(Collectors.toSet());

    @Override
    public List<? extends ValidationError> validate(TableDescription tableDescription) {
        List<ValidationError> errors = new ArrayList<>();
        for (CommonProperty commonProperty : getCommonProperties(tableDescription)) {
            errors.addAll(validateCommonProperty(commonProperty.getValue(), commonProperty.getIri()));
        }
        return errors;
    }

    private List<? extends ValidationError> validateCommonProperty(JsonNode jsonNode, String iri) {
        List<ValidationError> errors = new ArrayList<>();
        if (jsonNode.isObject()) {
            ObjectNode object = (ObjectNode) jsonNode;
            errors.addAll(validateValue(object, iri));
            object.fields().forEachRemaining(entry -> {
                if (CsvwKeywords.ID_PROPERTY.equals(entry.getKey())) {
                    boolean isValid = entry.getValue().isTextual()
                            && !entry.getValue().textValue().startsWith(BLANK_NODE_PREFIX);
                    if (!isValid) {
                        errors.add(ValidationError.fatal("error.commonProperty.invalidId", iri));
                    }
                } else if (CsvwKeywords.TYPE_PROPERTY.equals(entry.getKey())) {
                    boolean isValid = entry.getValue().isTextual()
                            && (UriUtils.isCommonProperty(entry.getValue().textValue())
                                || CsvwKeywords.terms.contains(entry.getValue().textValue()))
                            && !entry.getValue().textValue().startsWith(BLANK_NODE_PREFIX);
                    if (!isValid) {
                        errors.add(ValidationError.fatal("error.commonProperty.invalidType", iri));
                    }
                } else if (CsvwKeywords.CONTEXT_PROPERTY.equals(entry.getKey())) {
                    errors.add(ValidationError.fatal("error.commonProperty.hasContext", iri));
                } else if (CsvwKeywords.LIST.equals(entry.getKey())) {
                    errors.add(ValidationError.fatal("error.commonProperty.hasList", iri));
                } else if (CsvwKeywords.SET.equals(entry.getKey())) {
                    errors.add(ValidationError.fatal("error.commonProperty.hasSet", iri));
                } else if (entry.getKey().startsWith(SPECIAL_NODE_PREFIX) && !possibleSpecialKeys.contains(entry.getKey())) {
                    errors.add(ValidationError.fatal("error.commonProperty.fauxKeyword", iri, entry.getKey()));
                }
            });
        } else if (jsonNode.isArray()) {
            ArrayNode array = (ArrayNode) jsonNode;
            int i = 0;
            array.elements().forEachRemaining(element -> {
                errors.addAll(validateCommonProperty(element, iri + "." + String.valueOf(i)));
            });
        }
        return errors;
    }

    private List<? extends ValidationError> validateValue(ObjectNode object, String iri) {
        List<ValidationError> errors = new ArrayList<>();
        JsonNode type = object.get(CsvwKeywords.TYPE_PROPERTY);
        JsonNode language = object.get(CsvwKeywords.LANGUAGE_PROPERTY);
        JsonNode value = object.get(CsvwKeywords.VALUE_PROPERTY);
        if (value != null) {
            int numberOfProperties = object.size() - 1;
            if (!(value.isTextual() || value.isNumber() || value.isBoolean())) {
                errors.add(ValidationError.fatal("error.commonProperty.invalidValue", iri));
            }
            if (language != null && type != null) {
                errors.add(ValidationError.fatal("error.commonProperty.invalidCombination", iri));
            }

            if (type != null) {
                numberOfProperties--;
            }
            if (language != null) {
                numberOfProperties--;
                if (!language.isTextual() || !LanguageUtils.isLanguageTag(language.textValue())) {
                    errors.add(ValidationError.fatal("error.commonProperty.invalidLanguage", iri));
                }
            }
            if (numberOfProperties > 0) {
                errors.add(ValidationError.fatal("error.commonProperty.unknownProperties", iri));
            }
        }
        if (value == null && language != null) {
            errors.add(ValidationError.fatal("error.commonProperty.missingValue", iri));
        }
        return errors;
    }
}
