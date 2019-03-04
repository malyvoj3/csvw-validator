package com.malyvoj3.csvwvalidator.domain.metadata;

public interface Normalizable {

    // Normalizovat se muze s libovolnym kontextem (objekt vlastnost s retezcem muze mit po ziskani URL jiny kontext)
    void normalize(Context context);

}
