package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode
public class NaturalLanguageProperty extends Property<Map<String, List<String>>> {

    public NaturalLanguageProperty(Map<String, List<String>> value) {
        super(value);
    }

    @Override
    public List<ValidationError> normalize(Context context) {
        List<ValidationError> normalizationErrors = super.normalize(context);
        if (context.getLanguage() != null && value.containsKey(CsvwKeywords.NATURAL_LANGUAGE_CODE)) {
            // Replace UND with default language from context.
            value.put(context.getLanguage().getValue(), value.remove(CsvwKeywords.NATURAL_LANGUAGE_CODE));
        }
        return normalizationErrors;
    }

    public boolean hasIntersectionWith(NaturalLanguageProperty otherTitles) {
        for (String language : value.keySet()) {
            if (CsvwKeywords.NATURAL_LANGUAGE_CODE.equals(language)) {
                // If language is UND then it can be equal to any language.
                for (String title : value.get(language)) {
                    boolean titleExistInAnyLanguage = otherTitles.getValue().values().stream()
                            .flatMap(Collection::stream)
                            .anyMatch(otherTitle -> otherTitle.equals(title));
                    if (titleExistInAnyLanguage) {
                        return true;
                    }
                }
            } else {
                for (String title : value.get(language)) {
                    boolean titleExistInSameLanguage = otherTitles.getValue().get(language).contains(title);
                    boolean titleExistInDefaultLanguage = otherTitles.getValue().get(CsvwKeywords.NATURAL_LANGUAGE_CODE).contains(title);
                    if (titleExistInSameLanguage || titleExistInDefaultLanguage) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
