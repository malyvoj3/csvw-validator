package com.malyvoj3.csvwvalidator.processor;

import java.util.List;

/**
 * Class which locates metadata file (JSON-LD) descriptors for tabular data.
 */
public interface SchemaLocator {

    /**
     * Localized metadata files for tabular data.
     * @param tabularIri IRI of tabular data file.
     * @return List of IRIs, where can be the metadata file
     */
    List<String> getMetadataUris(String tabularIri);

}
