package com.malyvoj3.csvwvalidator.metadata.domain;

import com.malyvoj3.csvwvalidator.metadata.domain.properties.LinkProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.NaturalLanguageProperty;
import com.malyvoj3.csvwvalidator.metadata.domain.properties.StringAtomicProperty;

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
