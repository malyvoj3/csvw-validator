package com.malyvoj3.csvwvalidator.validation.metadata;

import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableGroupDescription;
import com.malyvoj3.csvwvalidator.validation.ValidationError;

import java.util.List;

public interface MetadataValidator {

    List<? extends ValidationError> validateTable(TableDescription tableDescription);

    List<? extends ValidationError> validateTableGroup(TableGroupDescription tableGroupDescription);
}
