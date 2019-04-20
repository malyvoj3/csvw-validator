package com.malyvoj3.csvwvalidator

import com.malyvoj3.csvwvalidator.domain.ValidationStatus
import com.malyvoj3.csvwvalidator.web.rest.BatchValidationResponse
import com.malyvoj3.csvwvalidator.web.rest.ValidationResponse
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CsvwControllerIT extends Specification {

    @LocalServerPort
    private int port

    def setup() throws Exception {
        RestAssured.port = port
    }

    @Unroll
    def "Rest controller - /validate endpoint (#name)"() {
        given: "Receive URL of tabular data and/or its metadata"
        def request = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)

        when: "Receive URL of tabular data and/or its metadata"
        def response = request.post("/validate");

        then: "Response has correct response code"
        response.statusCode() == statusCode

        and: "Response body has correct values"
        ValidationResponse body = response.getStatusCode() == 200 ? response.body().as(ValidationResponse.class) : new ValidationResponse()
        body.getValidationStatus() == expectedStatus
        body.getWarningCount() == expectedWarningCount
        body.getErrorCount() == expectedErrorCount
        body.getFatalCount() == expectedFatalCount
        body.getTotalErrorsCount() == expectedTotalErrorsCount
        body.getTabularUrl() == expectedTabularUrl
        body.getMetadataUrl() == expectedMetadaUrl

        where:
        name                                | requestBody                                                                                                                                                                | statusCode | expectedStatus           | expectedWarningCount | expectedErrorCount | expectedFatalCount | expectedTotalErrorsCount | expectedTabularUrl                              | expectedMetadaUrl
        "empty body"                        | "{}"                                                                                                                                                                       | 400        | null                     | null                 | null               | null               | null                     | null                                            | null
        "just tabular - strict"             | "{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\"}"                                                                                                      | 200        | ValidationStatus.WARNING | 2                    | 0                  | 0                  | 2                        | "http://www.w3.org/2013/csvw/tests/test010.csv" | null
        "just tabular - non strict"         | "{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\", \"strictMode\": false}"                                                                               | 200        | ValidationStatus.PASSED  | 0                    | 0                  | 0                  | 0                        | "http://www.w3.org/2013/csvw/tests/test010.csv" | null
        "just metadata - strict"            | "{\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\"}"                                                                                           | 200        | ValidationStatus.ERROR   | 1                    | 3                  | 0                  | 4                        | null                                            | "http://www.w3.org/2013/csvw/tests/test160-metadata.json"
        "just metadata - non strict"        | "{\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\", \"strictMode\": false}"                                                                    | 200        | ValidationStatus.ERROR   | 0                    | 3                  | 0                  | 3                        | null                                            | "http://www.w3.org/2013/csvw/tests/test160-metadata.json"
        "tabular and metadata - strict"     | "{\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test282-metadata.json\", \"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test282.csv\"}"                        | 200        | ValidationStatus.WARNING | 1                    | 0                  | 0                  | 1                        | "http://www.w3.org/2013/csvw/tests/test282.csv" | "http://www.w3.org/2013/csvw/tests/test282-metadata.json"
        "tabular and metadata - non strict" | "{\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test282-metadata.json\", \"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test282.csv\", \"strictMode\": false}" | 200        | ValidationStatus.PASSED  | 0                    | 0                  | 0                  | 0                        | "http://www.w3.org/2013/csvw/tests/test282.csv" | "http://www.w3.org/2013/csvw/tests/test282-metadata.json"
    }

    @Unroll
    def "Rest controller - /validateBatch endpoint (#name)"() {
        given: "Receive URL of tabular data and/or its metadata"
        def request = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)

        when: "Receive URL of tabular data and/or its metadata"
        def response = request.post("/validateBatch");

        then: "Response has correct response code"
        response.statusCode() == statusCode

        and: "Response body has correct values"
        BatchValidationResponse body = response.getStatusCode() == 200 ?
                response.body().as(BatchValidationResponse.class) : new BatchValidationResponse()
        body.getPassedFilesCount() == expectedPassedFilesCount
        body.getWarningFilesCount() == expectedWarningFilesCount
        body.getErrorFilesCount() == expectedErrorFilesCount
        body.getFilesCount() == expectedFilesCount
        (Long) body.getFilesResults().size() == expectedFileResults

        where:
        name                             | requestBody                                                                                                                                                                                                                                                                                                                                             | statusCode | expectedPassedFilesCount | expectedWarningFilesCount | expectedErrorFilesCount | expectedFilesCount | expectedFileResults
        "invalid body"                   | "{\"test\": adad}"                                                                                                                                                                                                                                                                                                                                      | 400        | null                     | null                      | null                    | null               | 0
        "empty body"                     | "{}"                                                                                                                                                                                                                                                                                                                                                    | 200        | 0                        | 0                         | 0                       | 0                  | 0
        "one tabular file"               | "{\"filesToProcess\": [{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\"}]}"                                                                                                                                                                                                                                                           | 200        | 0                        | 1                         | 0                       | 1                  | 1
        "one tabular file + empty"       | "{\"filesToProcess\": [{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\"}, {}]}"                                                                                                                                                                                                                                                       | 200        | 0                        | 1                         | 0                       | 1                  | 1
        "one tabular file + non-strict"  | "{\"filesToProcess\": [{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\"}], \"strictMode\": false}"                                                                                                                                                                                                                                    | 200        | 1                        | 0                         | 0                       | 1                  | 1
        "one metadata file"              | "{\"filesToProcess\": [{\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\"}]}"                                                                                                                                                                                                                                                | 200        | 0                        | 0                         | 1                       | 1                  | 1
        "one metadata file + empty"      | "{\"filesToProcess\": [{\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\"}, {}]}"                                                                                                                                                                                                                                            | 200        | 0                        | 0                         | 1                       | 1                  | 1
        "one metadata file + non-strict" | "{\"filesToProcess\": [{\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\"}], \"strictMode\": false}"                                                                                                                                                                                                                         | 200        | 0                        | 0                         | 1                       | 1                  | 1
        "multiple files"                 | "{\"filesToProcess\": [{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\"}, {\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\"}, {\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test282-metadata.json\", \"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test282.csv\"}]}"                        | 200        | 0                        | 2                         | 1                       | 3                  | 3
        "multiple files + empty"         | "{\"filesToProcess\": [{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\"}, {\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\"}, {\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test282-metadata.json\", \"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test282.csv\"}, {}]}"                    | 200        | 0                        | 2                         | 1                       | 3                  | 3
        "multiple files + non-strict"    | "{\"filesToProcess\": [{\"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test010.csv\"}, {\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test160-metadata.json\"}, {\"metadataUrl\": \"http://www.w3.org/2013/csvw/tests/test282-metadata.json\", \"tabularUrl\": \"http://www.w3.org/2013/csvw/tests/test282.csv\"}], \"strictMode\": false}" | 200        | 2                        | 0                         | 1                       | 3                  | 3
    }

}
