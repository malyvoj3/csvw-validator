package com.malyvoj3.csvwvalidator.domain.metadata.properties

import spock.lang.Specification
import spock.lang.Unroll

class NaturalLanguagePropertyTest extends Specification {

    @Unroll
    def "Has intersection test"() {
        when: "Receive two natural language properties"
        NaturalLanguageProperty first = new NaturalLanguageProperty(firstMap)
        NaturalLanguageProperty second = new NaturalLanguageProperty(secondMap)
        boolean hasIntersection = first.hasIntersectionWith(second);

        then: "Find out if exist intersection between these two natural language properties"
        hasIntersection == expectedValue

        where:
        firstMap                                                                           | secondMap                                                      | expectedValue
        ['cs': ['Geralt']]                                                                 | ['cs': ['Geralt']]                                             | true
        ['en': ['Geralt']]                                                                 | ['en-GB': ['Geralt']]                                          | true
        ['en-US': ['Geralt']]                                                              | ['en-GB': ['Geralt']]                                          | true
        ['und': ['Geralt']]                                                                | ['en-GB': ['Geralt']]                                          | true
        ['de': ['Geralt']]                                                                 | ['und': ['Geralt']]                                            | true
        ['cs': ['Geralt']]                                                                 | ['cs': ['Ciri']]                                               | false
        ['cs': ['Geralt', 'Ciri', 'Yennefer']]                                             | ['cs': ['Marigold', 'Cahir']]                                  | false
        ['cs': ['Geralt', 'Ciri', 'Yennefer'], 'en': ['Cahir']]                            | ['en': ['Ciri'], 'cs': ['Cahir']]                              | false
        ['cs': ['Geralt', 'Ciri', 'Yennefer'], 'und': ['Ciri']]                            | ['und': ['Ciri'], 'cs': ['Geralt']]                            | true
        ['und': ['Geralt', 'Ciri', 'Yennefer']]                                            | ['und': ['Yarpen', 'Zoltan', 'Regis']]                         | false
        ['cs': ['Geralt', 'Ciri'], en: ['Yennefer', 'Triss'], de: ['Dandelion', 'Zoltan']] | ['und': ['Yarpen', 'Zoltan', 'Regis']]                         | true
        ['cs': ['Geralt', 'Ciri'], en: ['Yennefer', 'Triss'], de: ['Dandelion', 'Zoltan']] | ['fr': ['Yarpen', 'Zoltan'], 'it': ['Dijkstra', 'Vilgefortz']] | false
    }

}
