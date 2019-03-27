package com.malyvoj3.csvwvalidator.validation;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.descriptions.*;
import com.malyvoj3.csvwvalidator.domain.metadata.properties.*;
import com.malyvoj3.csvwvalidator.domain.model.*;
import com.malyvoj3.csvwvalidator.parser.csv.CsvParsingResult;
import com.malyvoj3.csvwvalidator.utils.CsvwKeywords;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

// Z metadat vytvori anotace
public class AnnotationCreator {

    public TableGroup createAnnotations(List<CsvParsingResult> csvParsingResults, TableGroupDescription tableGroupDescription) {
        String baseLanguage = createBaseLanguage(tableGroupDescription);
        InheritedProperties defaultProperties = createInheritedProperties(tableGroupDescription);

        TableGroup tableGroup = new TableGroup();
        tableGroup.setId(getValue(tableGroupDescription.getId()));
        tableGroup.setNotes(createNotes(tableGroupDescription.getNotes()));
        List<Table> tables = new ArrayList<>();

        for (CsvParsingResult csvParsingResult : csvParsingResults) {
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

    public Table createAnnotations(CsvParsingResult csvParsingResult, TableDescription tableDescription) {
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
        // TODO rows/cells

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
            columns.add(createColumn(tmpColumn, tmpDescription, baseLanguage, inheritedProperties));
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

    private InheritedProperties createInheritedProperties(InheritanceDescription description) {
        InheritedProperties inheritedProperties = new InheritedProperties();
        if (description != null) {
            inheritedProperties.setAboutUrl(getValue(description.getAboutUrl()));
            // TODO datatype
            inheritedProperties.setDatatype(null);
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
        if (main.getNullValue() == null) {
            main.setNullValue(Arrays.stream(toMerge)
                    .filter(Objects::nonNull)
                    .map(InheritedProperties::getNullValue)
                    .filter(Objects::nonNull)
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
        private Datatype datatype;
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
