package com.malyvoj3.csvwvalidator.parser.tabular;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvFormatException extends Exception {

    private ValidationError validationError;

}
