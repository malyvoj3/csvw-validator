package com.malyvoj3.csvwvalidator.utils

import spock.lang.Specification
import spock.lang.Unroll

class LanguageUtilsTest extends Specification {

    @Unroll
    def "String is valid BCP47 language tag '#tag' - #expectedValue"() {
        when: "Receive string"
        boolean isValid = LanguageUtils.isLanguageTag(tag)

        then: "Validate string if it is valid language tag"
        isValid == expectedValue

        where:
        tag                                                 | expectedValue
        "en"                                                | true
        "en-US"                                             | true
        "cs"                                                | true
        "de"                                                | true
        "zh"                                                | true
        "zh-Latn"                                           | true
        "zh-Latn-CN"                                        | true
        "zh-Latn-CN-variant1"                               | true
        "zh-Latn-CN-variant1-a-extend1"                     | true
        "zh-Latn-CN-variant1-a-extend1-x-wadegile"          | true
        "zh-Latn-CN-variant1-a-extend1-x-wadegile-private1" | true
        "fooooooooo"                                        | false
        "a-bad-language"                                    | false
    }

    @Unroll
    def "Language tags equals #expectedValue - '#firstTag' and '#secondTag'."() {
        when: "Receive two strings"
        boolean areEquals = LanguageUtils.equalsLanguageTags(firstTag, secondTag)

        then: "Compare them if their smallest truncated versions by BCP47 are equal"
        areEquals == expectedValue

        where:
        firstTag                        | secondTag                                           | expectedValue
        "und"                           | ""                                                  | false
        null                            | "und"                                               | false
        "fooooooooo"                    | "und"                                               | false
        "und"                           | "a-bad-language"                                    | false
        "und"                           | "und"                                               | true
        "und"                           | "en"                                                | true
        "en"                            | "und"                                               | true
        "en"                            | "en"                                                | true
        "en"                            | "en-US"                                             | true
        "en-US"                         | "en-GB"                                             | true
        "zh"                            | "zh-Latn-CN-variant1-a-extend1-x-wadegile-private1" | true
        "zh-Latn-CN-variant1-a-extend1" | "zh-Latn-CN"                                        | true
    }

}
