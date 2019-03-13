package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import java.util.List;
import java.util.Map;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import com.malyvoj3.csvwvalidator.utils.LanguageUtils;
import com.malyvoj3.csvwvalidator.validation.ValidationError;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
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

    public boolean hasIntersectionWith(@NonNull NaturalLanguageProperty otherTitles) {
        for (String firstLanguage : value.keySet()) {
            for (String secondLanguage : otherTitles.getValue().keySet()) {
                if (LanguageUtils.equalsLanguageTags(firstLanguage, secondLanguage)) {
                    List<String> firstTitles = value.get(firstLanguage);
                    List<String> secondTitles = otherTitles.getValue().get(secondLanguage);
                    if (arrayHasIntersection(firstTitles, secondTitles)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean arrayHasIntersection(@NonNull List<String> firstTitles, @NonNull List<String> secondTitles) {
        for (String first : firstTitles) {
            for (String second : secondTitles) {
                if (first.equals(second)) {
                    return true;
                }
            }
        }
        return false;
    }

}
