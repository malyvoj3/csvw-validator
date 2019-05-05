package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.ObjectMapper;
import com.malyvoj3.csvwvalidator.persistance.domain.ResultEntity;
import com.malyvoj3.csvwvalidator.persistance.repository.ResultRepository;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.PersistentResult;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
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
    public BatchProcessingResult<PersistentResult> process(ProcessingSettings settings, List<ProcessingInput> inputs) {
        BatchProcessingResult<ProcessingResult> processingResult = processor.process(settings, inputs);
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
    public PersistentResult process(ProcessingSettings settings, String tabularFile, String tabularFileName, String metadataFile, String metadataFileName) {
        ProcessingResult processingResult = processor.process(settings, tabularFile, tabularFileName, metadataFile, metadataFileName);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult process(ProcessingSettings settings, String tabularUrl, String metadataUrl) {
        ProcessingResult processingResult = processor.process(settings, tabularUrl, metadataUrl);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processTabularData(ProcessingSettings settings, String tabularFile, String fileName) {
        ProcessingResult processingResult = processor.processTabularData(settings, tabularFile, fileName);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processMetadata(ProcessingSettings settings, String metadataFile, String fileName) {
        ProcessingResult processingResult = processor.processMetadata(settings, metadataFile, fileName);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processTabularData(ProcessingSettings settings, String tabularUrl, InputStream metadataFile, String metadataFileName) {
        ProcessingResult processingResult = processor.processTabularData(settings, tabularUrl, metadataFile, metadataFileName);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processMetadata(ProcessingSettings settings, String metadataUrl) {
        ProcessingResult processingResult = processor.processMetadata(settings, metadataUrl);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }

    @Override
    public PersistentResult processTabularData(ProcessingSettings settings, String tabularUrl) {
        ProcessingResult processingResult = processor.processTabularData(settings, tabularUrl);
        ResultEntity resultEntity = objectMapper.toResult(processingResult);
        resultEntity = repository.save(resultEntity);
        return objectMapper.toResult(resultEntity);
    }
}
