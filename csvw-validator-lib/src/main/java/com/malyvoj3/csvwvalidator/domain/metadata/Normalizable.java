package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;

import java.util.List;

/**
 * Object which can be normalized with {@link Context}.
 */
public interface Normalizable {

    // Normalizovat se muze s libovolnym kontextem (objekt vlastnost s retezcem muze mit po ziskani URL jiny kontext)

    /**
     * Normalize object with given context.
     * @param context JSON-LD context in form of CSV on the Web.
     * @return List of errors, which occurs during normalization process.
     */
    List<ValidationError> normalize(Context context);

}
