package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.ObjectMapper;
import com.malyvoj3.csvwvalidator.persistance.domain.ResultEntity;
import com.malyvoj3.csvwvalidator.persistance.repository.ResultRepository;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.PersistentResult;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersistentProcessor implements Processor<PersistentResult, BatchProcessingResult<PersistentResult>> {

    private final Processor<ProcessingResult, BatchProcessingResult<ProcessingResult>> processor;
    private final ResultRepository repository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PersistentProcessor(Processor<ProcessingResult, BatchProcessingResult<ProcessingResult>> processor, ResultRepository repository, ObjectMapper objectMapper) {
        this.processor = processor;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public BatchProcessingResult<PersistentResult> process(ProcessingContext context, List<ProcessingInput> inputs) {
        BatchProcessingResult<ProcessingResult> processingResult = processor.process(context, inputs);
        List<ResultEntity> resultEntities = new ArrayList<>();
        for (ProcessingResult result : processingResult.getFilesResults()) {
            resultEntities.add(objectMapper.toResult(result));
        }
        Iterable<ResultEntity> savedResults = repository.saveAll(resultEntities);
        processingResult.setFilesResults(null);
        BatchProcessingResult<PersistentResult> batchResult = objectMapper.toPersistentResult(processingResult);
        List<PersistentResult> persistentResults = new ArrayList<>();
        for (ResultEntity savedResult : savedResults) {
            persistentResults.add(objectMapper.toResult(savedResult));
        }
        batchResult.setFilesResults(persistentResults);
        return batchResult;
    }

    @Override
    public PersistentResult process(ProcessingContext context, String tabularFile, String tabularFileName, String metadataFile, String metadataFileName) {
        ProcessingResult processingResult = processor.process(context, tabularFile, tabularFileName, metadataFile, metadataFileName);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult process(ProcessingContext context, String tabularUrl, String metadataUrl) {
        ProcessingResult processingResult = processor.process(context, tabularUrl, metadataUrl);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processTabularData(ProcessingContext context, String tabularFile, String fileName) {
        ProcessingResult processingResult = processor.processTabularData(context, tabularFile, fileName);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processMetadata(ProcessingContext context, String metadataFile, String fileName) {
        ProcessingResult processingResult = processor.processMetadata(context, metadataFile, fileName);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processMetadata(ProcessingContext context, String metadataUrl) {
        ProcessingResult processingResult = processor.processMetadata(context, metadataUrl);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processTabularData(ProcessingContext context, String tabularUrl) {
        ProcessingResult processingResult = processor.processTabularData(context, tabularUrl);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }
}
