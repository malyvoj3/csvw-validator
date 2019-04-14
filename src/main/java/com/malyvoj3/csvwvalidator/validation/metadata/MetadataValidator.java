package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;

import java.util.List;

public interface MetadataValidator {

    List<? extends ValidationError> validateTable(TableDescription tableDescription);

    List<? extends ValidationError> validateTableGroup(TableGroupDescription tableGroupDescription);
}
