package com.malyvoj3.csvwvalidator.domain.metadata.properties

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.malyvoj3.csvwvalidator.domain.metadata.Context
import spock.lang.Specification
import spock.lang.Unroll

class CommonPropertyTest extends Specification {

    @Unroll
    def "Test normalization of value in common property"() {
        given: "Common property with text value"
        ObjectMapper objectMapper = new ObjectMapper()
        JsonNode jsonNode = objectMapper.readTree(json)
        CommonProperty commonProperty = new CommonProperty(jsonNode, null)

        and: "context with base language set to 'en'"
        Context context = new Context();
        context.setLanguage(new StringAtomicProperty("en"))
        context.setBase(new StringAtomicProperty("http://example.com"))

        when: "Normalize common property"
        commonProperty.normalize(context)

        then: "It is correctly normalized"
        objectMapper.writeValueAsString(commonProperty.getValue()) == expectedJson
        where:
        json                                                     | expectedJson
        "42"                                                     | "42"
        "true"                                                   | "true"
        "3.14"                                                   | "3.14"
        "null"                                                   | "null"
        "[42,true,3.14,null]"                                    | "[42,true,3.14,null]"
        "\"Tyrion\""                                             | "{\"@value\":\"Tyrion\",\"@language\":\"en\"}"
        "[\"Jaime\",\"Cersei\",false]"                           | "[{\"@value\":\"Jaime\",\"@language\":\"en\"},{\"@value\":\"Cersei\",\"@language\":\"en\"},false]"
        "{\"@value\":\"Jon Snow\"}"                              | "{\"@value\":\"Jon Snow\"}"
        "{\"@id\":\"foaf:Person\"}"                              | "{\"@id\":\"http://xmlns.com/foaf/0.1/Person\"}"
        "{\"@id\":\"seven-kingdoms\",\"@type\":\"fantasy saga\"," +
                "\"integer\":6,\"boolean\":true,\"decimal\":3.14,\"null\":null," +
                "\"gameOfThrones\":{\"@id\":\"gameOfThrones\"," +
                "\"@type\":\"book\",\"integer2\":6,\"author\":\"G.R.R. Martin\"}," +
                "\"array\":[{\"@id\":\"gameOfThrones\"," +
                "\"@type\":\"book\",\"integer2\":6},null,true]}" |
                "{\"@id\":\"http://example.com/seven-kingdoms\"," +
                "\"@type\":\"fantasy saga\",\"integer\":6," +
                "\"boolean\":true,\"decimal\":3.14,\"null\":null," +
                "\"gameOfThrones\":{\"@id\":\"http://example.com/gameOfThrones\"," +
                "\"@type\":\"book\",\"integer2\":6,\"author\":{\"@value\":\"G.R.R. Martin\",\"@language\":\"en\"}}," +
                "\"array\":[{\"@id\":\"http://example.com/gameOfThrones\",\"@type\":\"book\",\"integer2\":6},null,true]}"
    }

}
