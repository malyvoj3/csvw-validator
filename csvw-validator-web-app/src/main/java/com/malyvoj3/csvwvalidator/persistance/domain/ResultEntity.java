package com.malyvoj3.csvwvalidator.persistance.domain;

import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ResultEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String tabularUrl;
    private String metadataUrl;
    private ValidationStatus validationStatus;

    @ElementCollection(targetClass = ErrorEntity.class, fetch = FetchType.EAGER)
    private List<ErrorEntity> errors;

    @Embedded
    private ProcessingSettings settings;
    private Long warningCount;
    private Long errorCount;
    private Long fatalCount;
    private Long totalErrorsCount;
    private String usedLanguage;

}