package com.malyvoj3.csvwvalidator.processor;

import java.util.List;

public interface SchemaLocator {

    List<String> getMetadataUris(String csvUri);

}
