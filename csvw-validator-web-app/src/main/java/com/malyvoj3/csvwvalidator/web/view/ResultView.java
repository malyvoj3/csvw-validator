package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.ObjectMapper;
import com.malyvoj3.csvwvalidator.domain.ValidationStatus;
import com.malyvoj3.csvwvalidator.persistance.domain.ResultEntity;
import com.malyvoj3.csvwvalidator.persistance.repository.ResultRepository;
import com.malyvoj3.csvwvalidator.processor.result.CsvResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.LocalizedError;
import com.malyvoj3.csvwvalidator.processor.result.PersistentResult;
import com.malyvoj3.csvwvalidator.processor.result.RdfResultWriter;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import com.malyvoj3.csvwvalidator.web.view.components.ErrorGrid;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedLabel;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedParamLabel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@HtmlImport("styles/validator-styles.html")
@Route(value = "result", layout = CsvwLayout.class)
@PageTitle("Result")
public class ResultView extends MainLayout implements HasUrlParameter<String> {

    private static final String NOT_AVAILABLE = "N/A";

    private final ResultRepository resultRepository;
    private final CsvResultWriter csvResultWriter;
    private final RdfResultWriter rdfResultWriter;
    private final ObjectMapper objectMapper;

    private String resultId;

    @Autowired
    public ResultView(ResultRepository resultRepository,
                      CsvResultWriter csvResultWriter,
                      RdfResultWriter rdfResultWriter,
                      ObjectMapper objectMapper) {
        this.resultRepository = resultRepository;
        this.csvResultWriter = csvResultWriter;
        this.rdfResultWriter = rdfResultWriter;
        this.objectMapper = objectMapper;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        resultId = parameter;
        add(createHeader("header.result"));
        ResultEntity resultEntity = null;
        if (resultId != null) {
            resultEntity = resultRepository.findById(resultId).orElse(null);
        }
        if (resultEntity != null) {
            add(createResult(objectMapper.toResult(resultEntity)));
        } else {
            buildEmptyResult();
        }
    }

    private Component createResult(PersistentResult result) {
        VerticalLayout resultDiv = new VerticalLayout();
        VerticalLayout upperGrid = new VerticalLayout();

        LocalizedButton csvButton = new LocalizedButton("result.csvResults");
        FileDownloadWrapper csvButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.csv", () -> new ByteArrayInputStream(csvResultWriter.writeResult(result))));
        csvButtonWrapper.wrapComponent(csvButton);
        LocalizedButton rdfButton = new LocalizedButton("result.rdfResults");
        FileDownloadWrapper rdfButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.ttl", () -> new ByteArrayInputStream(rdfResultWriter.writeResult(result))));
        rdfButtonWrapper.wrapComponent(rdfButton);

        Component resultStatusLabel = createColorResult(result.getValidationStatus());
        Component resultIcon = createResultIcon(result.getValidationStatus());
        LocalizedLabel resultLabel = new LocalizedLabel("result.status");
        HorizontalLayout resultGroup = new HorizontalLayout(resultLabel, resultStatusLabel, resultIcon);
        upperGrid.add(resultGroup);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                resultGroup);

        LocalizedParamLabel idLabel = new LocalizedParamLabel("result.id", result.getId());
        upperGrid.add(idLabel);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                idLabel);

        if (result.getTabularUrl() != null) {
            LocalizedParamLabel csvLabel = new LocalizedParamLabel("result.csvUrl", result.getTabularUrl());
            upperGrid.add(csvLabel);
            upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                    csvLabel);
        }
        if (result.getMetadataUrl() != null) {
            LocalizedParamLabel metadataLabel = new LocalizedParamLabel("result.metadataUrl", result.getMetadataUrl());
            upperGrid.add(metadataLabel);
            upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                    metadataLabel);
        }

        LocalizedParamLabel tablesLabel = new LocalizedParamLabel("result.processTables",
                result.getTablesNumber());
        upperGrid.add(tablesLabel);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                tablesLabel);
        LocalizedParamLabel rowsLabel = new LocalizedParamLabel("result.processRows",
                result.getTablesNumber() == 0 ? NOT_AVAILABLE : result.getRowsNumber());
        upperGrid.add(rowsLabel);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                rowsLabel);
        LocalizedParamLabel columnsLabel = new LocalizedParamLabel("result.processColumns",
                result.getTablesNumber() == 0 ? NOT_AVAILABLE : result.getColumnsNumber());
        upperGrid.add(columnsLabel);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.START,
                columnsLabel);

        HorizontalLayout downloadButtons = new HorizontalLayout(csvButtonWrapper, rdfButtonWrapper);
        upperGrid.add(downloadButtons);
        upperGrid.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                downloadButtons);
        upperGrid.setClassName("validation-upperGrid");
        resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                upperGrid);
        resultDiv.add(upperGrid);

        List<LocalizedError> items = new ArrayList<>(result.getErrors());
        if (!items.isEmpty()) {
            ErrorGrid errorGrid = new ErrorGrid(items);
            resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH,
                    errorGrid);
            resultDiv.add(errorGrid);
        }

        HorizontalLayout validateButtons = new HorizontalLayout();
        LocalizedButton revalidateButton = createRevalidateButton(result);
        if (revalidateButton != null) {
            resultDiv.add(revalidateButton);
            resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                    revalidateButton);
            validateButtons.add(revalidateButton);
        }
        LocalizedButton newValidationButton = newValidationButton();
        resultDiv.add(newValidationButton);
        resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                newValidationButton);
        validateButtons.add(newValidationButton);

        resultDiv.add(validateButtons);
        resultDiv.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,
                validateButtons);

        resultDiv.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        upperGrid.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        return resultDiv;
    }

    private Component createColorResult(ValidationStatus validationStatus) {
        LocalizedLabel label;
        switch (validationStatus) {
            case PASSED:
                label = new LocalizedLabel("result.passed");
                label.addClassName("result-passed");
                break;
            case WARNING:
                label = new LocalizedLabel("result.warning");
                label.addClassName("result-warning");
                break;
            case ERROR:
                label = new LocalizedLabel("result.error");
                label.addClassName("result-error");
                break;
            default:
                throw new IllegalStateException(String.format("Invalid validation status %s", validationStatus));
        }
        return label;
    }

    private Component createResultIcon(ValidationStatus validationStatus) {
        Icon icon;
        switch (validationStatus) {
            case PASSED:
                icon = new Icon(VaadinIcon.CHECK_CIRCLE);
                icon.addClassName("result-passed");
                break;
            case WARNING:
                icon = new Icon(VaadinIcon.EXCLAMATION_CIRCLE);
                icon.addClassName("result-warning");
                break;
            case ERROR:
                icon = new Icon(VaadinIcon.CLOSE_CIRCLE);
                icon.addClassName("result-error");
                break;
            default:
                throw new IllegalStateException(String.format("Invalid validation status %s", validationStatus));
        }
        return icon;
    }

    private LocalizedButton createRevalidateButton(PersistentResult result) {
        String csvUrl = UriUtils.normalizeUri(result.getTabularUrl());
        String metadataUrl = UriUtils.normalizeUri(result.getMetadataUrl());
        LocalizedButton revalidateButton = null;
        if (UriUtils.isHttpUri(csvUrl) || UriUtils.isHttpUri(metadataUrl)) {
            revalidateButton = new LocalizedButton("result.revalidate");
            revalidateButton.addClassName("page-middle-button");
            revalidateButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
            LocalizedButton finalRevalidateButton = revalidateButton;
            revalidateButton.addClickListener(event -> {
                ValidatingDataDTO bean = new ValidatingDataDTO();
                finalRevalidateButton.getUI().ifPresent(ui -> bean.setLocale(ui.getLocale()));
                bean.setStrictMode(result.isStrictMode());
                bean.setCsvUrl(csvUrl);
                bean.setMetadataUrl(metadataUrl);
                ComponentUtil.setData(UI.getCurrent(), ValidatingDataDTO.class, bean);
                finalRevalidateButton.getUI().ifPresent(ui -> ui.navigate(ValidatingView.class));
            });
        }
        return revalidateButton;
    }

    private LocalizedButton newValidationButton() {
        LocalizedButton validationButton = new LocalizedButton("result.validationAgain");
        validationButton.addClassName("page-middle-button");
        validationButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        validationButton.addClickListener(event -> {
            validationButton.getUI().ifPresent(ui -> ui.navigate(ValidationView.class));
        });
        return validationButton;
    }

    private void buildEmptyResult() {
        LocalizedLabel label = new LocalizedLabel("result.invalidResultId");
        label.addClassName("page-text");
        add(label);
        LocalizedButton validationButton = new LocalizedButton("main.startValidation");
        validationButton.addClassName("page-middle-button");
        validationButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        validationButton.addClickListener(event -> {
            validationButton.getUI().ifPresent(ui -> ui.navigate("validation"));
        });
        add(validationButton);
    }
}
