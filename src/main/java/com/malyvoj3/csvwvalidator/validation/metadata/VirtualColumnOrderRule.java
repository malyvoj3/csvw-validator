package com.malyvoj3.csvwvalidator.validation.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.domain.metadata.Property;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.ColumnDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.SchemaDescription;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.TableDescription;

public class VirtualColumnOrderRule extends TableDescriptionValidationRule {

  @Override
  public List<? extends ValidationError> validate(TableDescription tableDescription) {
    List<ValidationError> errors = new ArrayList<>();
    List<ColumnDescription> columns = Optional.ofNullable(tableDescription)
        .map(TableDescription::getTableSchema)
        .map(Property::getValue)
        .map(SchemaDescription::getColumns)
        .map(Property::getValue)
        .orElse(Collections.emptyList());

    boolean isValid = true;

    Integer lastNonVirtual = null;
    for (int i = 0; i < columns.size(); i++) {
      ColumnDescription column = columns.get(i);
      boolean isVirtual = column.getVirtual() != null && column.getVirtual().getValue();
      if (isVirtual) {
        lastNonVirtual = i - 1;
      } else if (lastNonVirtual != null) {
        // We have non virtual but there was already some virtual.
        isValid = false;
        break;
      }
    }

    if (!isValid) {
      errors.add(ValidationError.error(
          "Table '%s' does not have virtual columns after all non-virtual columns.", tableDescription.getUrl().getValue()
      ));
    }
    return errors;
  }

}
