package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;

import java.util.List;

public interface Normalizable {

    // Normalizovat se muze s libovolnym kontextem (objekt vlastnost s retezcem muze mit po ziskani URL jiny kontext)
    List<ValidationError> normalize(Context context);

}
