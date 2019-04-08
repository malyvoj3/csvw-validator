package com.malyvoj3.csvwvalidator.parser.metadata.parsers.properties.column;

import com.fasterxml.jackson.databind.JsonNode;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.JsonProperty;
import com.malyvoj3.csvwvalidator.parser.metadata.parsers.PropertyParser;
import com.malyvoj3.csvwvalidator.validation.JsonParserError;
import lombok.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamePropertyParser<T extends ColumnDescription> implements PropertyParser<T> {

    private static final String INVALID_PREFIX = "_";
    private static final String URI_TEMPLATE_VARIABLE_PATTERN = "(((\\w|(%[0-9a-fA-F]{2}))+(\\.(\\w|(%[0-9a-fA-F]{2}))+)*)(:" +
            "\\d{0,4})?)(,(((\\w|(%[0-9a-fA-F]{2}))+(\\.(\\w|(%[0-9a-fA-F]{2}))+)*)(:\\d{0,4})?))*";

    @Override
    public void parsePropertyToDescription(@NonNull T description,
                                           @NonNull JsonProperty jsonProperty) {
        JsonNode property = jsonProperty.getJsonValue();
        StringAtomicProperty name = null;
        if (property.isTextual() && !property.textValue().startsWith(INVALID_PREFIX) && isUriTemplateVariable(property.textValue())) {
            name = new StringAtomicProperty(property.textValue());
        } else {
            jsonProperty.addError(JsonParserError.invalidPropertyType(jsonProperty.getName()));
        }
        description.setName(name);
    }

    private boolean isUriTemplateVariable(String string) {
        Pattern pattern = Pattern.compile(URI_TEMPLATE_VARIABLE_PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
