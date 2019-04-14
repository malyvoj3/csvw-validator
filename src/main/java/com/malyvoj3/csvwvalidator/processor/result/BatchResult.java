package com.malyvoj3.csvwvalidator.processor.result;

import java.util.List;

public interface BatchResult<T extends Result> {

    List<T> getFilesResults();

}
