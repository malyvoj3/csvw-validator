package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.CsvwKeywords;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColumnDescription extends InheritanceDescription implements CommonDescription, CompatibleDescription<ColumnDescription> {

    private List<CommonProperty> commonProperties = new ArrayList<>();

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    private StringAtomicProperty name;

    private BooleanAtomicProperty suppressOutput;

    private NaturalLanguageProperty titles;

    private BooleanAtomicProperty virtual;

    @Override
    public void addCommonProperty(CommonProperty commonProperty) {
        commonProperties.add(commonProperty);
    }

    @Override
    public boolean isCompatibleWith(@NonNull ColumnDescription other) {
        boolean compatible;
        if (name == null && titles == null && other.getName() == null && other.getTitles() == null) {
            compatible = true;
        } else if ((name == null && other.getName() == null) || name.equals(other.getName())) {
            compatible = true;
        } else {
            compatible = existsTitleIntersection(titles, other.getTitles());
        }
        return compatible;
    }

    // TODO: Put to LangProperty? and test method properly (flatMap)
    private boolean existsTitleIntersection(NaturalLanguageProperty titles, NaturalLanguageProperty otherTitles) {
        for (String language : titles.getValues().keySet()) {
            if (CsvwKeywords.NATURAL_LANGUAGE_CODE.equals(language)) {
                // If language is UND then it can be equal to any language.
                for (String title : titles.getValues().get(language)) {
                    boolean titleExistInAnyLanguage = otherTitles.getValues().values().stream()
                            .flatMap(Collection::stream)
                            .anyMatch(otherTitle -> otherTitle.equals(title));
                    if (titleExistInAnyLanguage) {
                        return true;
                    }
                }
            } else {
                for (String title : titles.getValues().get(language)) {
                    boolean titleExistInSameLanguage = otherTitles.getValues().get(language).contains(title);
                    boolean titleExistInDefaultLanguage = otherTitles.getValues().get(CsvwKeywords.NATURAL_LANGUAGE_CODE).contains(title);
                    if (titleExistInSameLanguage || titleExistInDefaultLanguage) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
