package com.malyvoj3.csvwvalidator.parser.csv;

import com.malyvoj3.csvwvalidator.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvFormatException extends Exception {

    private ValidationError validationError;

}
