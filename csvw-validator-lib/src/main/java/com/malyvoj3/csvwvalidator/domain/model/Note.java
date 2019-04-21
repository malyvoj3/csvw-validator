package com.malyvoj3.csvwvalidator.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class Note {

    private String iri;
    private JsonNode value;
}
