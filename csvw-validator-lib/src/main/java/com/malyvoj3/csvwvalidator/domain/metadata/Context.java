package com.malyvoj3.csvwvalidator.domain.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.properties.StringAtomicProperty;
import lombok.Data;

/**
 * JSON-LD context with restrictions of CSV on the Web.
 */
@Data
public class Context {

    private StringAtomicProperty refContext;
    private StringAtomicProperty base;
    private StringAtomicProperty language;

}
