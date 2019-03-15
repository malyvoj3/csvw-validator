package com.malyvoj3.csvwvalidator.utils

import spock.lang.Specification
import spock.lang.Unroll

class UriUtilsTest extends Specification {

    @Unroll
    def "Is string valid http/https URI test"() {
        when: "Receive string"
        boolean isValid = UriUtils.isValidUri(uriString)

        then: "Validate string if it is valid URI"
        isValid == expectedValue

        where:
        uriString                                                                               | expectedValue
        "hTtP://a/./b/../b/%63/%7bfoo%7d"                                                       | true
        "http://example.com"                                                                    | true
        "https://example.com"                                                                   | true
        "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top" | true
        "https://en.wiktionary.org/wiki/Ῥόδος"                                                  | true
        "mailto:John.Doe@example.com"                                                           | false
        "foo"                                                                                   | false
        ""                                                                                      | false
        "file://example.com"                                                                    | true
        "file://localhost/etc/fstab"                                                            | true
        "file:///etc/fstab"                                                                     | true
        "file://localhost/c\$/WINDOWS/clock.avi"                                                | true
        "file:///c:/WINDOWS/clock.avi"                                                          | true
        "file://hostname/path/to/the%20file.txt"                                                | true
    }

    @Unroll
    def "Http URIs normalization test"() {
        when: "Receive URI"
        String normalizedUri = UriUtils.normalizeUri(uri)

        then: "Is correctly normalized by RFC 3986"
        normalizedUri == expectedUri

        where: "Examples mostly from RFC 3986 specification"
        uri                                                         | expectedUri
        "hTtP://a/./b/../b/%63/%7bfoo%7d"                           | "http://a/b/c/%7Bfoo%7D"
        "hTtPS://a/./b/../b/%63/%7bfoo%7d"                          | "https://a/b/c/%7Bfoo%7D"
        "fILe://a/./b/../b/%63/%7bfoo%7d"                           | "file://a/b/c/%7Bfoo%7D"
        "http://example.com"                                        | "http://example.com/"
        "http://example.com/"                                       | "http://example.com/"
        "http://example.com:/"                                      | "http://example.com/"
        "http://example.com:80/"                                    | "http://example.com/"
        "https://example.com"                                       | "https://example.com/"
        "file://example.com"                                        | "file://example.com/"
        "https://example.com/"                                      | "https://example.com/"
        "https://example.com:/"                                     | "https://example.com/"
        "https://example.com:443/"                                  | "https://example.com/"
        "https://en.wiktionary.org/wiki/Ῥόδος"                      | "https://en.wiktionary.org/wiki/%E1%BF%AC%CF%8C%CE%B4%CE%BF%CF%82"
        "http://example.com/Příliš žluťoučký-kůň%úpěl*ďábelské-ódy" | "http://example.com/P%C5%99%C3%ADli%C5%A1%20%C5%BElu%C5%A5ou%C4%8Dk%C3%BD-k%C5%AF%C5%88%25%C3%BAp%C4%9Bl*%C4%8F%C3%A1belsk%C3%A9-%C3%B3dy"
        "file://example.com/Příliš žluťoučký-kůň%úpěl*ďábelské-ódy" | "file://example.com/P%C5%99%C3%ADli%C5%A1%20%C5%BElu%C5%A5ou%C4%8Dk%C3%BD-k%C5%AF%C5%88%25%C3%BAp%C4%9Bl*%C4%8F%C3%A1belsk%C3%A9-%C3%B3dy"
        "mailto:John.Doe@example.com"                               | null
        "foo"                                                       | null
    }

    @Unroll
    def "Http URIs equals test"() {
        when: "Receive two URIs"
        boolean areEquals = UriUtils.uriEquals(firstUri, secondUri)

        then: "They are normalized and compared if they are equals"
        areEquals == same

        where: "Examples mostly from RFC 3986 specification"
        firstUri                           | secondUri                                     | same
        "hTtP://a/./b/../b/%63/%7bfoo%7d"  | "http://a/b/c/%7Bfoo%7D"                      | true
        "hTtPS://a/./b/../b/%63/%7bfoo%7d" | "https://a/b/c/%7Bfoo%7D"                     | true
        "fILe://a/./b/../b/%63/%7bfoo%7d"  | "fILe://a/b/c/%7Bfoo%7D"                      | true
        "http://example.com"               | "http://example.com/"                         | true
        "http://example.com/"              | "http://example.com/"                         | true
        "http://example.com:/"             | "http://example.com/"                         | true
        "file://example.com:/"             | "file://example.com/"                         | true
        "http://example.com:80/"           | "http://example.com/"                         | true
        "https://example.com"              | "https://example.com/"                        | true
        "https://example.com/"             | "https://example.com/"                        | true
        "https://example.com:/"            | "https://example.com/"                        | true
        "https://example.com:443/"         | "https://example.com/"                        | true
        "http://e.com/Příliš ž"            | "http://e.com/P%C5%99%C3%ADli%C5%A1%20%C5%BE" | true
        "http://e.com/Příliš ž"            | "invalid"                                     | false
        ""                                 | "invalid"                                     | false
        "https://example.com"              | null                                          | false
    }

    @Unroll
    def "Http URIs resolving test"() {
        when: "Receive Base URI and relative URI"
        String resolvedUri = UriUtils.resolveUri(baseUri, uri)

        then: "Relative URI is correctly normalized and resolved against base URI"
        UriUtils.uriEquals(resolvedUri, expectedUri)

        where: "Examples mostly from RFC 3986 specification"
        baseUri                                                             | uri                     | expectedUri
        "http://a/b/c/d;p?q"                                                | "http:g"                | "http:g"
        "http://a/b/c/d;p?q"                                                | "g"                     | "http://a/b/c/g"
        "http://a/b/c/d;p?q"                                                | "./g"                   | "http://a/b/c/g"
        "http://a/b/c/d;p?q"                                                | "g/"                    | "http://a/b/c/g/"
        "http://a/b/c/d;p?q"                                                | "/g"                    | "http://a/g"
        "http://a/b/c/d;p?q"                                                | "//g"                   | "http://g"
        "http://a/b/c/d;p?q"                                                | "?y"                    | "http://a/b/c/d;p?y"
        "http://a/b/c/d;p?q"                                                | "g?y"                   | "http://a/b/c/g?y"
        "http://a/b/c/d;p?q"                                                | "#s"                    | "http://a/b/c/d;p?q#s"
        "http://a/b/c/d;p?q"                                                | "g#s"                   | "http://a/b/c/g#s"
        "http://a/b/c/d;p?q"                                                | "g?y#s"                 | "http://a/b/c/g?y#s"
        "http://a/b/c/d;p?q"                                                | ";x"                    | "http://a/b/c/;x"
        "http://a/b/c/d;p?q"                                                | "g;x"                   | "http://a/b/c/g;x"
        "http://a/b/c/d;p?q"                                                | "g;x?y#s"               | "http://a/b/c/g;x?y#s"
        "http://a/b/c/d;p?q"                                                | ""                      | "http://a/b/c/d;p?q"
        "http://a/b/c/d;p?q"                                                | "."                     | "http://a/b/c/"
        "http://a/b/c/d;p?q"                                                | "./"                    | "http://a/b/c/"
        "http://a/b/c/d;p?q"                                                | ".."                    | "http://a/b/"
        "http://a/b/c/d;p?q"                                                | "../"                   | "http://a/b/"
        "http://a/b/c/d;p?q"                                                | "../g"                  | "http://a/b/g"
        "http://a/b/c/d;p?q"                                                | "../.."                 | "http://a/"
        "http://a/b/c/d;p?q"                                                | "../../"                | "http://a/"
        "http://a/b/c/d;p?q"                                                | "../../g"               | "http://a/g"
        "http://a/b/c/d;p?q"                                                | "../../../g"            | "http://a/g"
        "http://a/b/c/d;p?q"                                                | "../../../../g"         | "http://a/g"
        "http://a/b/c/d;p?q"                                                | "/./g"                  | "http://a/g"
        "http://a/b/c/d;p?q"                                                | "/../g"                 | "http://a/g"
        "http://a/b/c/d;p?q"                                                | "g."                    | "http://a/b/c/g."
        "http://a/b/c/d;p?q"                                                | ".g"                    | "http://a/b/c/.g"
        "http://a/b/c/d;p?q"                                                | "g.."                   | "http://a/b/c/g.."
        "http://a/b/c/d;p?q"                                                | "..g"                   | "http://a/b/c/..g"
        "http://a/b/c/d;p?q"                                                | "./../g"                | "http://a/b/g"
        "http://a/b/c/d;p?q"                                                | "./g/."                 | "http://a/b/c/g/"
        "http://a/b/c/d;p?q"                                                | "g/./h"                 | "http://a/b/c/g/h"
        "http://a/b/c/d;p?q"                                                | "g/../h"                | "http://a/b/c/h"
        "http://a/b/c/d;p?q"                                                | "g;x=1/./y"             | "http://a/b/c/g;x=1/y"
        "http://a/b/c/d;p?q"                                                | "g;x=1/../y"            | "http://a/b/c/y"
        "http://a/b/c/d;p?q"                                                | "g?y/./x"               | "http://a/b/c/g?y/./x"
        "http://a/b/c/d;p?q"                                                | "g?y/../x"              | "http://a/b/c/g?y/../x"
        "http://a/b/c/d;p?q"                                                | "g#s/./x"               | "http://a/b/c/g#s/./x"
        "http://a/b/c/d;p?q"                                                | "g#s/../x"              | "http://a/b/c/g#s/../x"
        "https://dev.nkod.opendata.cz/soubor/datové-sady.csv-metadata.json" | "datové-sady.csv"       | "https://dev.nkod.opendata.cz/soubor/datové-sady.csv"
        "http://example.com"                                                | "http://foo.com"        | "http://foo.com"
        "http://example.org/south-west/devon.csv "                          | "/.well-known/csvm "    | "http://example.org/.well-known/csvm"
        "file://example.com/test.csv"                                       | "g"                     | "file://example.com/g"
        "file:///c:/path/to/the%20file.txt"                                 | "../directory/test.txt" | "file:///c:/path/directory/test.txt"
    }

    @Unroll
    def "Resolve '#uriString' to #expectedValue"() {
        when: "Receive string"
        String resolvedUri = UriUtils.resolveCommonProperty(uriString)

        then: "Resolve string to normalized absolute URI (if it was common property)"
        resolvedUri == expectedValue

        where:
        uriString                              | expectedValue
        null                                   | null
        ""                                     | null
        "foo"                                  | null
        ":"                                    | null
        ":aaaa"                                | null
        "hTtP://a/./b/../b/%63/%7bfoo%7d"      | "http://a/b/c/%7Bfoo%7D"
        "http://example.com"                   | "http://example.com/"
        "https://example.com"                  | "https://example.com/"
        "https://en.wiktionary.org/wiki/Ῥόδος" | "https://en.wiktionary.org/wiki/%E1%BF%AC%CF%8C%CE%B4%CE%BF%CF%82"
        "dc:description"                       | "http://purl.org/dc/terms/description"
        "dcat:Catalog"                         | "http://www.w3.org/ns/dcat#Catalog"
        "rdf:type"                             | "http://www.w3.org/1999/02/22-rdf-syntax-ns#type"
        "schema:address"                       | "http://schema.org/address"
        "csvw:Table"                           | "http://www.w3.org/ns/csvw#Table"
        "foaf:Person"                          | "http://xmlns.com/foaf/0.1/Person"
        "foo:test"                             | null
        "a:test"                               | null
        "err:test"                             | null
    }

    @Unroll
    def "Is '#uriString' valid common property - #expectedValue"() {
        when: "Receive string"
        boolean isValid = UriUtils.isCommonProperty(uriString)

        then: "Validate string if it is valid common property"
        isValid == expectedValue

        where:
        uriString                                                                               | expectedValue
        null                                                                                    | false
        ""                                                                                      | false
        "foo"                                                                                   | false
        ":"                                                                                     | false
        ":aaaa"                                                                                 | false
        "hTtP://a/./b/../b/%63/%7bfoo%7d"                                                       | true
        "http://example.com"                                                                    | true
        "https://example.com"                                                                   | true
        "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top" | true
        "https://en.wiktionary.org/wiki/Ῥόδος"                                                  | true
        "dc:description"                                                                        | true
        "dcat:Catalog"                                                                          | true
        "rdf:type"                                                                              | true
        "schema:address"                                                                        | true
        "csvw:Table"                                                                            | true
        "foaf:Person"                                                                           | true
        "foo:test"                                                                              | false
        "a:test"                                                                                | false
        "err:test"                                                                              | false
    }

}
