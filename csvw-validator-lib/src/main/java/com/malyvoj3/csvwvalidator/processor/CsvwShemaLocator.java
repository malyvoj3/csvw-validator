package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.domain.FileResponse;
import com.malyvoj3.csvwvalidator.utils.FileUtils;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CsvwShemaLocator implements SchemaLocator {

    private static final String WELL_KNOWN_URI = "/.well-known/csvm";
    private static final List<String> defaultLocations = Stream.of("{+url}-metadata.json", "csv-metadata.json")
            .collect(Collectors.toList());

    @Override
    public List<String> getMetadataUris(String csvUri) {
        List<String> metadataUris = new ArrayList<>();

        // Get possible locations of metadata from site-wide configuration.
        String wellKnownLocation = UriUtils.resolveUri(csvUri, WELL_KNOWN_URI);
        Map<String, Object> uriVariable = Collections.singletonMap("url", csvUri);
        List<String> uriTemplates = Collections.emptyList();
        try {
            uriTemplates = getUriTemplates(wellKnownLocation);
        } catch (IOException e) {
            log.error(String.format("Error during parsing site-wide configuration with url '%s'", wellKnownLocation));
        }
        for (String uriTemplate : uriTemplates) {
            metadataUris.add(UriUtils.expandAndResolveTemplate(csvUri, uriTemplate, uriVariable));
        }

        // Get default locations of metadata.
        for (String uriTemplate : defaultLocations) {
            metadataUris.add(UriUtils.expandAndResolveTemplate(csvUri, uriTemplate, uriVariable));
        }
        return metadataUris;
    }

    private List<String> getUriTemplates(String wellKnownLocation) throws IOException {
        List<String> templates = new ArrayList<>();
        FileResponse fileResponse = FileUtils.downloadTabularFile(wellKnownLocation);
        if (fileResponse != null && fileResponse.getContent() != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(fileResponse.getContent())))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("<!DOCTYPE html")) {
                        log.warn("Well known URL is HTML, skipping it.");
                        templates = new ArrayList<>();
                        break;
                    }
                    templates.add(line);
                }
            }
        }
        return templates;
    }
}
