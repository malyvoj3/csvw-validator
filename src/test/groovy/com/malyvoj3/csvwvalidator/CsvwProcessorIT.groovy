package com.malyvoj3.csvwvalidator

import com.malyvoj3.csvwvalidator.processor.CsvwProcessor
import com.malyvoj3.csvwvalidator.validation.ValidationError
import com.malyvoj3.csvwvalidator.validation.ValidationStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class CsvwProcessorIT extends Specification {

    @Autowired
    private CsvwProcessor csvwProcessor

    @Unroll
    def "#name - CSVW implementation report"() {
        when: "Receive URL of tabular data and/or its metadata"
        List<? extends ValidationError> errors
        if (metadataUrl != null) {
            errors = csvwProcessor.processTabularData(tabularUrl, metadataUrl)
        } else {
            errors = csvwProcessor.processTabularData(tabularUrl)
        }
        then: "It is successfully validated"
        errors.size() == 2
        where:
        name | tabularUrl | metadataUrl | expectedResult
        "test001" | "http://www.w3.org/2013/csvw/tests/test001.csv" | null | ValidationStatus.PASSED
        "test005" | "http://www.w3.org/2013/csvw/tests/test005.csv" | null | ValidationStatus.PASSED
        "test006" | "http://www.w3.org/2013/csvw/tests/test006.csv" | null | ValidationStatus.PASSED
        "test007" | "http://www.w3.org/2013/csvw/tests/test007.csv" | null | ValidationStatus.PASSED
        "test008" | "http://www.w3.org/2013/csvw/tests/test008.csv" | null | ValidationStatus.PASSED
        "test009" | "http://www.w3.org/2013/csvw/tests/test009.csv" | null | ValidationStatus.PASSED
        "test010" | "http://www.w3.org/2013/csvw/tests/test010.csv" | null | ValidationStatus.PASSED
        "test011" | "http://www.w3.org/2013/csvw/tests/test011/tree-ops.csv" | null | ValidationStatus.PASSED
        "test012" | "http://www.w3.org/2013/csvw/tests/test012/tree-ops.csv" | null | ValidationStatus.PASSED
        "test013" | "http://www.w3.org/2013/csvw/tests/test013/tree-ops.csv" | "http://www.w3.org/2013/csvw/tests/test013-user-metadata.json" | ValidationStatus.PASSED
        "test014" | "http://www.w3.org/2013/csvw/tests/test014/tree-ops.csv" | null | ValidationStatus.PASSED
        "test015" | "http://www.w3.org/2013/csvw/tests/test015/tree-ops.csv" | "http://www.w3.org/2013/csvw/tests/test015/user-metadata.json" | ValidationStatus.PASSED
        "test016" | "http://www.w3.org/2013/csvw/tests/test016/tree-ops.csv" | null | ValidationStatus.PASSED
        "test017" | "http://www.w3.org/2013/csvw/tests/test017/tree-ops.csv" | null | ValidationStatus.PASSED
        "test018" | "http://www.w3.org/2013/csvw/tests/test018/tree-ops.csv" | "http://www.w3.org/2013/csvw/tests/test018/user-metadata.json" | ValidationStatus.PASSED
        "test023" | "http://www.w3.org/2013/csvw/tests/tree-ops.csv" | "https://w3c.github.io/csvw/tests/reports/index.html#test_67f964a94cd7b26f36b32ef111590152" | ValidationStatus.PASSED
        "test027" | "http://www.w3.org/2013/csvw/tests/tree-ops.csv" | "http://www.w3.org/2013/csvw/tests/test027-user-metadata.json" | ValidationStatus.PASSED

    }

}
