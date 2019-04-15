package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.domain.DataTypeFactory;
import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.*;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import com.malyvoj3.csvwvalidator.domain.model.*;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeDefinition;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.DataTypeFormatException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.IncomparableDataTypeException;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.ValueType;
import com.malyvoj3.csvwvalidator.domain.model.datatypes.string.StringType;
import com.malyvoj3.csvwvalidator.parser.tabular.TabularParsingResult;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class DefaultAnnotationCreator implements AnnotationCreator {

    private final DataTypeFactory dataTypeFactory;

    @Override
    public TableGroup createAnnotations(List<TabularParsingResult> csvParsingResults, TableGroupDescription tableGroupDescription) {
        String baseLanguage = createBaseLanguage(tableGroupDescription);
        InheritedProperties defaultProperties = createInheritedProperties(tableGroupDescription);

        TableGroup tableGroup = new TableGroup();
        tableGroup.setId(getValue(tableGroupDescription.getId()));
        tableGroup.setNotes(createNotes(tableGroupDescription.getNotes()));
        List<Table> tables = new ArrayList<>();

        for (TabularParsingResult csvParsingResult : csvParsingResults) {
            Table extractedTable = csvParsingResult.getTable();
            TableDescription tableDescription = tableGroupDescription.getTables().getValue().stream()
                    .filter(desc -> desc.describesTabularData(extractedTable.getUrl()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Table group description does not describes tabular data."));
            tables.add(createTable(extractedTable, tableDescription, baseLanguage, defaultProperties));
        }
        tableGroup.setTables(tables);

        return tableGroup;
    }

    @Override
    public Table createAnnotations(TabularParsingResult csvParsingResult, TableDescription tableDescription) {
        String baseLanguage = createBaseLanguage(tableDescription);
        return createTable(csvParsingResult.getTable(), tableDescription, baseLanguage, null);
    }

    private Table createTable(Table extractedTable,
                              TableDescription tableDescription,
                              String baseLanguage,
                              InheritedProperties tableGroupProperties) {
        SchemaDescription tableSchema = tableDescription.getTableSchema() != null ?
                tableDescription.getTableSchema().getValue() : null;
        InheritedProperties defaultProperties = mergeInheritedProperties(
                createInheritedProperties(tableSchema),
                createInheritedProperties(tableDescription),
                tableGroupProperties
        );

        extractedTable.setUrl(tableDescription.getUrl().getValue());
        extractedTable.setSuppressOutput(getValue(tableDescription.getSuppressOutput()));
        extractedTable.setTableDirection(createTableDirection(tableDescription.getTableDirection()));
        extractedTable.setNotes(createNotes(tableDescription.getNotes()));
        extractedTable.setColumns(createColumns(extractedTable, tableDescription, baseLanguage, defaultProperties));
        // TODO transformations
        // TODO foreignKeys
        addPrimaryKeys(extractedTable.getRows(), tableDescription.getTableSchema());

        return extractedTable;
    }

    private List<Column> createColumns(Table extractedTable,
                                       TableDescription tableDescription,
                                       String baseLanguage,
                                       InheritedProperties defaultProperties) {
        SchemaDescription schemaDescription = tableDescription.getTableSchema().getValue();
        List<Column> extractedColumns = extractedTable.getColumns();
        List<ColumnDescription> columnDescriptions = schemaDescription.getColumns().getValue();
        List<Column> columns = new ArrayList<>();
        // Schema should be compatible -> same non-virtual columns.
        int nonVirtualSize = extractedColumns.size();
        for (int i = 0; i < nonVirtualSize; i++) {
            Column tmpColumn = extractedColumns.get(i);
            ColumnDescription tmpDescription = columnDescriptions.get(i);
            InheritedProperties inheritedProperties = mergeInheritedProperties(
                    createInheritedProperties(tmpDescription),
                    defaultProperties
            );
            Column annotatedColumn = createColumn(tmpColumn, tmpDescription, baseLanguage, inheritedProperties);
            annotatedColumn.setCells(createCells(annotatedColumn));
            columns.add(annotatedColumn);
        }
        // Add virtual columns.
        for (int i = extractedColumns.size(); i < columnDescriptions.size(); i++) {
            ColumnDescription tmpDescription = columnDescriptions.get(i);
            InheritedProperties inheritedProperties = mergeInheritedProperties(
                    createInheritedProperties(tmpDescription),
                    defaultProperties
            );
            columns.add(createColumn(tmpDescription, baseLanguage, inheritedProperties));
        }
        return columns;
    }

    private List<Cell> createCells(Column annotatedColumn) {
        List<Cell> cells = annotatedColumn.getCells();
        DataType dataType = annotatedColumn.getDatatype() != null ? annotatedColumn.getDatatype() : createDefaultDataType();
        for (Cell cell : cells) {
            ValueType value;
            String normalizedValue = normalizeCellValue(cell.getStringValue(), dataType);
            normalizedValue = StringUtils.isNotEmpty(normalizedValue) ? normalizedValue : cell.getColumn().getDefaultValue();
            // TODO LIST values
            if (isNullValue(normalizedValue, cell)) {
                value = null;
                if (cell.getColumn().isRequired()) {
                    String errorMsg = String.format("Cell value (row %d column %d) is null, but column is required.",
                            cell.getRow().getNumber(), cell.getColumn().getNumber());
                    cell.getErrors().add(new CellError("REQUIRED", errorMsg));
                }
            } else {
                try {
                    value = dataTypeFactory.createDataType(cell.getStringValue(), dataType);
                    boolean isValid = validateConstraints(value, dataType);
                    if (!isValid) {
                        String errorMsg = String.format("Cell value (row %d column %d) does not satisfy the " +
                                "constraints of datatype.", cell.getRow().getNumber(), cell.getColumn().getNumber());
                        cell.getErrors().add(new CellError("INVALID VALUE", errorMsg));
                    }
                } catch (DataTypeFormatException | IncomparableDataTypeException e) {
                    String errorMsg = String.format("Cell (row %d column %d) cannot be formatted as '%s' " +
                            "datatype.", cell.getRow().getNumber(), cell.getColumn().getNumber(), dataType.getBase());
                    cell.getErrors().add(new CellError("INVALID VALUE FORMAT", errorMsg));
                    value = new StringType(normalizedValue);
                }
            }
            cell.setValue(value);
            // TODO URI templates - propertyURL, valueURL
        }
        return cells;
    }

    private DataType createDefaultDataType() {
        return DataType.builder()
                .base(CsvwKeywords.STRING_DATA_TYPE)
                .build();
    }

    private boolean isNullValue(String normalizedValue, Cell cell) {
        List<String> nullValues = Optional.ofNullable(cell)
                .map(Cell::getColumn)
                .map(Column::getNullValues)
                .orElse(Collections.emptyList());
        return normalizedValue == null || nullValues.contains(normalizedValue);
    }

    private String normalizeCellValue(String cellValue, DataType dataType) {
        String normalizedValue = cellValue;
        if (StringUtils.isNotEmpty(cellValue)) {
            boolean isStringType = CsvwKeywords.STRING_DATA_TYPE.equals(dataType.getBase())
                    || CsvwKeywords.JSON_DATA_TYPE.equals(dataType.getBase())
                    || CsvwKeywords.HTML_DATA_TYPE.equals(dataType.getBase())
                    || CsvwKeywords.XML_DATA_TYPE.equals(dataType.getBase())
                    || CsvwKeywords.ANY_ATOMIC_DATA_TYPE.equals(dataType.getBase());
            if (!isStringType) {
                normalizedValue = cellValue.replaceAll("\\t|[\\r\\n]+", " ");
            }
            if (!isStringType && !CsvwKeywords.NORMALIZED_STRING_DATA_TYPE.equals(dataType.getBase())) {
                normalizedValue = StringUtils.normalizeSpace(normalizedValue);
            }
        }
        return normalizedValue;
    }

    private boolean validateConstraints(ValueType value, DataType dataType) throws DataTypeFormatException, IncomparableDataTypeException {
        if (value.isLengthDataType()) {
            if (dataType.getLength() != null && !dataType.getLength().equals(value.getLength())) {
                return false;
            }
            if (dataType.getMinLength() != null && dataType.getMinLength() > value.getLength()) {
                return false;
            }
            if (dataType.getMaxLength() != null && dataType.getMaxLength() < value.getLength()) {
                return false;
            }
        }
        if (value.isValueDataType()) {
            if (dataType.getMinimum() != null) {
                ValueType minimumValue = dataTypeFactory.createDataType(dataType.getMinimum(), dataType);
                if (!value.isGreaterEq(minimumValue)) {
                    return false;
                }
            }
            if (dataType.getMaximum() != null) {
                ValueType maximumValue = dataTypeFactory.createDataType(dataType.getMaximum(), dataType);
                if (!value.isLowerEq(maximumValue)) {
                    return false;
                }
            }
            if (dataType.getMinExclusive() != null) {
                ValueType minExclusiveValue = dataTypeFactory.createDataType(dataType.getMinExclusive(), dataType);
                if (!value.isGreater(minExclusiveValue)) {
                    return false;
                }
            }
            if (dataType.getMaxExclusive() != null) {
                ValueType maxExclusiveValue = dataTypeFactory.createDataType(dataType.getMaxExclusive(), dataType);
                if (!value.isLower(maxExclusiveValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    private InheritedProperties createInheritedProperties(InheritanceDescription description) {
        InheritedProperties inheritedProperties = new InheritedProperties();
        if (description != null) {
            inheritedProperties.setAboutUrl(getValue(description.getAboutUrl()));
            inheritedProperties.setDatatype(createDataType(description.getDataType()));
            inheritedProperties.setDefaultValue(getValue(description.getDefaultValue()));
            inheritedProperties.setLang(getValue(description.getLang()));
            inheritedProperties.setNullValue(description.getNullValue() != null ? description.getNullValue().getValue() : new ArrayList<>());
            inheritedProperties.setOrdered(getValue(description.getOrdered()));
            inheritedProperties.setPropertyUrl(getValue(description.getPropertyUrl()));
            inheritedProperties.setRequired(getValue(description.getRequired()));
            inheritedProperties.setSeparator(getValue(description.getSeparator()));
            inheritedProperties.setTextDirection(createTextDirection(description.getTextDirection()));
            inheritedProperties.setValueUrl(getValue(description.getValueUrl()));
        }
        return inheritedProperties;
    }

    private String createBaseLanguage(TopLevelDescription topLevelDescription) {
        return Optional.ofNullable(topLevelDescription.getContext())
                .map(Context::getLanguage)
                .map(StringAtomicProperty::getValue)
                .orElse(CsvwKeywords.NATURAL_LANGUAGE_CODE);
    }

    private TextDirection createTextDirection(StringAtomicProperty textDirection) {
        TextDirection result = null;
        if (textDirection != null) {
            switch (textDirection.getValue()) {
                case "auto":
                    result = TextDirection.AUTO;
                    break;
                case "rtl":
                    result = TextDirection.RIGHT_TO_LEFT;
                    break;
                case "ltr":
                    result = TextDirection.LEFT_TO_RIGHT;
                    break;
                case "inherit":
                    result = TextDirection.INHERIT;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private DataType createDataType(AtomicProperty<DataTypeDescription> dataTypeProp) {
        DataType dataType = null;
        if (dataTypeProp != null && dataTypeProp.getValue() != null) {
            DataTypeDescription desc = dataTypeProp.getValue();
            String base = getValue(desc.getBase());
            DataTypeDefinition dataTypeDefinition = DataTypeDefinition.getByName(base);
            dataType = DataType.builder()
                    .id(dataTypeDefinition != null ? dataTypeDefinition.getUrl() : null)
                    .base(base)
                    .format(createFormat(desc.getFormat()))
                    .length(getValue(desc.getLength()))
                    .minLength(getValue(desc.getMinLength()))
                    .maxLength(getValue(desc.getMaxLength()))
                    .minimum(getValue(desc.getMinimum()) != null ? getValue(desc.getMinimum()) : getValue(desc.getMinInclusive()))
                    .maximum(getValue(desc.getMaximum()) != null ? getValue(desc.getMaximum()) : getValue(desc.getMaxInclusive()))
                    .minExclusive(getValue(desc.getMinExclusive()))
                    .maxExclusive(getValue(desc.getMaxExclusive()))
                    .build();

        }
        return dataType;
    }

    private Format createFormat(AtomicProperty<FormatDescription> formatProp) {
        Format format = null;
        if (formatProp != null && formatProp.getValue() != null) {
            FormatDescription desc = formatProp.getValue();
            format = Format.builder()
                    .decimalChar(getValue(desc.getDecimalChar()))
                    .groupChar(getValue(desc.getGroupChar()))
                    .pattern(getValue(desc.getPattern()))
                    .build();

        }
        return format;
    }

    private List<Note> createNotes(ArrayProperty<NoteDescription> noteDescriptions) {
        List<Note> notes = new ArrayList<>();
        if (noteDescriptions != null) {
            notes = noteDescriptions.getValue().stream()
                    .map(noteDescription -> {
                        Note note = null;
                        CommonProperty commonProperty = noteDescription.getNote();
                        if (commonProperty != null) {
                            note = new Note();
                            note.setIri(commonProperty.getIri());
                            note.setValue(commonProperty.getValue());
                        }
                        return note;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return notes;
    }

    private TableDirection createTableDirection(StringAtomicProperty tableDirection) {
        TableDirection result = null;
        if (tableDirection != null) {
            switch (tableDirection.getValue()) {
                case "auto":
                    result = TableDirection.AUTO;
                    break;
                case "rtl":
                    result = TableDirection.RIGHT_TO_LEFT;
                    break;
                case "ltr":
                    result = TableDirection.LEFT_TO_RIGHT;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private Column createColumn(ColumnDescription columnDescription,
                                String baseLanguage,
                                InheritedProperties inheritedProperties) {
        return createColumn(Column.builder().build(), columnDescription, baseLanguage, inheritedProperties);
    }

    private Column createColumn(Column column,
                                ColumnDescription columnDescription,
                                String baseLanguage,
                                InheritedProperties inheritedProperties) {
        column.setName(getValue(columnDescription.getName()));
        column.setSuppressOutput(getValue(columnDescription.getSuppressOutput()));
        column.setTitles(getValue(columnDescription.getTitles()));
        column.setVirtual(getValue(columnDescription.getSuppressOutput()));
        column.setAboutUrl(inheritedProperties.getAboutUrl());
        column.setDatatype(inheritedProperties.getDatatype());
        column.setDefaultValue(inheritedProperties.getDefaultValue());
        column.setLang(inheritedProperties.getLang());
        column.setNullValues(inheritedProperties.getNullValue());
        column.setPropertyUrl(inheritedProperties.getPropertyUrl());
        column.setSeparator(inheritedProperties.getSeparator());
        column.setTextDirection(inheritedProperties.getTextDirection());
        column.setValueUrl(inheritedProperties.getValueUrl());
        column.setRequired(getValue(columnDescription.getRequired()));
        if (column.getName() == null) {
            String name;
            List<String> baseLanguageTitles = column.getTitles().get(baseLanguage);
            if (baseLanguageTitles != null && baseLanguageTitles.size() > 0) {
                // TODO percent encoding
                name = baseLanguageTitles.get(0);
            } else {
                name = String.format("_col.[%d]", column.getNumber());
            }
            column.setName(name);
        }
        return column;
    }

    private void addPrimaryKeys(List<Row> rows, ObjectProperty<SchemaDescription> schema) {
        if (schema != null && schema.getValue() != null) {
            SchemaDescription schemaDescription = schema.getValue();
            if (schemaDescription.getPrimaryKey() != null && schemaDescription.getPrimaryKey().getValue() != null) {
                List<String> columnNames = schemaDescription.getPrimaryKey().getValue();
                for (Row row : rows) {
                    row.setPrimaryKey(getPrimaryKeyCells(row, columnNames));
                }
            }

        }
    }

    private List<Cell> getPrimaryKeyCells(Row row, List<String> primaryColumnNames) {
        List<Cell> rowCells = row.getCells();
        List<Cell> primaryKeyCells = new ArrayList<>();
        for (String columnName : primaryColumnNames) {
            for (Cell cell : rowCells) {
                if (columnName.equals(cell.getColumn().getName())) {
                    primaryKeyCells.add(cell);
                }
            }
        }
        return primaryKeyCells;
    }

    private Map<String, List<String>> getValue(NaturalLanguageProperty property) {
        Map<String, List<String>> value = new HashMap<>();
        if (property != null) {
            value = property.getValue();
        }
        return value;
    }

    private Boolean getValue(BooleanAtomicProperty property) {
        boolean value = false;
        if (property != null) {
            value = property.getValue();
        }
        return value;
    }

    private String getValue(StringAtomicProperty property) {
        String value = null;
        if (property != null) {
            value = property.getValue();
        }
        return value;
    }

    private String getValue(UriTemplateProperty property) {
        String value = null;
        if (property != null) {
            value = property.getValue();
        }
        return value;
    }

    private String getValue(LinkProperty property) {
        String value = null;
        if (property != null) {
            value = property.getValue();
        }
        return value;
    }

    private Long getValue(IntegerAtomicProperty property) {
        Long value = null;
        if (property != null) {
            value = property.getValue();
        }
        return value;
    }

    private InheritedProperties mergeInheritedProperties(InheritedProperties main, InheritedProperties... toMerge) {
        if (main.getAboutUrl() == null) {
            main.setAboutUrl(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getAboutUrl)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        if (main.getDatatype() == null) {
            main.setDatatype(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getDatatype)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        if (main.getDefaultValue() == null) {
            main.setDefaultValue(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getDefaultValue)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        if (main.getLang() == null) {
            main.setLang(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getLang)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        if (main.getNullValue() == null || main.getNullValue().isEmpty()) {
            main.setNullValue(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getNullValue)
                    .filter(nullValues -> nullValues != null && !nullValues.isEmpty())
                    .findFirst()
                    .orElse(new ArrayList<>()));
        }
        if (main.getPropertyUrl() == null) {
            main.setPropertyUrl(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getPropertyUrl)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        if (main.getSeparator() == null) {
            main.setSeparator(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getSeparator)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        if (main.getTextDirection() == null) {
            main.setTextDirection(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getTextDirection)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        if (main.getValueUrl() == null) {
            main.setValueUrl(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getValueUrl)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null));
        }
        return main;
    }

    @Data
    class InheritedProperties {
        private String aboutUrl;
        private DataType datatype;
        private String defaultValue;
        private String lang;
        private List<String> nullValue = new ArrayList<>();
        private boolean ordered;
        private String propertyUrl;
        private boolean required;
        private String separator;
        private TextDirection textDirection;
        private String valueUrl;
    }
}
