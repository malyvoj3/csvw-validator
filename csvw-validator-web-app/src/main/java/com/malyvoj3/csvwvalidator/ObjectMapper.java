package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.persistance.domain.ErrorEntity;
import com.malyvoj3.csvwvalidator.persistance.domain.ResultEntity;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.LocalizedError;
import com.malyvoj3.csvwvalidator.processor.result.PersistentResult;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import org.mapstruct.Mapper;

@Mapper
public abstract class ObjectMapper {

    public abstract ErrorEntity toErrorEntity(LocalizedError localizedError);
    public abstract LocalizedError toLocalizedError(ErrorEntity errorEntity);
    public abstract ResultEntity toResult(ProcessingResult processingResult);
    public abstract PersistentResult toResult(ResultEntity processingResult);
    public abstract BatchProcessingResult<PersistentResult> toPersistentResult(BatchProcessingResult<ProcessingResult> processingResult);
}
