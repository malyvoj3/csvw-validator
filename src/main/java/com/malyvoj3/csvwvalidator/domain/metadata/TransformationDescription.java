package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;

public class TransformationDescription {

    // do abstraktniho predka?
    private LinkProperty id;
    private StringAtomicProperty type;

    //required
    private LinkProperty url;
    private LinkProperty scriptFormat;
    private LinkProperty targetFormat;

    //optional
    private StringAtomicProperty source;
    private NaturalLanguageProperty titles;
}
