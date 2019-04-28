package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.domain.ValidationError;
import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.result.CsvResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.RdfResultWriter;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedLabel;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedParamLabel;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Push
@HtmlImport("styles/validator-styles.html")
@Route(value = "result", layout = CsvwLayout.class)
@PageTitle("Result")
public class ResultView extends MainLayout {

    private final CsvwProcessor csvwProcessor;
    private final CsvResultWriter csvResultWriter;
    private final RdfResultWriter rdfResultWriter;

    public ResultView(@Autowired CsvwProcessor csvwProcessor,
                      @Autowired CsvResultWriter csvResultWriter,
                      @Autowired RdfResultWriter rdfResultWriter) {
        this.csvwProcessor = csvwProcessor;
        this.csvResultWriter = csvResultWriter;
        this.rdfResultWriter = rdfResultWriter;

        add(createHeader("header.result"));
        ValidatingDataDTO bean = ComponentUtil.getData(UI.getCurrent(), ValidatingDataDTO.class);
        if (bean != null) {
            ComponentUtil.setData(UI.getCurrent(), ValidatingDataDTO.class, null);
            buildResult(bean);
        } else {
            buildEmptyResult();
        }
    }

    private void buildEmptyResult() {
        LocalizedLabel label = new LocalizedLabel("result.requestExpired");
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

    private void buildResult(ValidatingDataDTO bean) {
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(bean.getStrictMode() == null ? true : bean.getStrictMode());
        boolean isTabularUpload = false;/*tabularUpload != null && tabularDataFile != null;*/
        boolean isMetadataUpload = false;/*metadataUpload != null && metadataFile != null;*/
        boolean isCsvUrl = StringUtils.isNotBlank(bean.getCsvUrl());
        boolean isMetadataUrl = StringUtils.isNotBlank(bean.getMetadataUrl());
        if (isTabularUpload && isMetadataUpload) {
            //showResult(csvwProcessor.processTabularData(settings, tabularDataFile, tabularDataFileName, metadataFile, metadataFileName));
        } else if (isCsvUrl && isMetadataUrl) {
            showResult(csvwProcessor.processTabularData(settings, bean.getCsvUrl(), bean.getMetadataUrl()));
        } else if (isTabularUpload && isMetadataUrl) {
            //Notification.show("Unsupported combination: uploaded tabular data file and metadata url!");
        } else if (isCsvUrl && isMetadataUpload) {
            //showResult(csvwProcessor.processTabularData(settings, tabularTextfield.getValue(), metadataFile, metadataFileName));
        } else if (isMetadataUrl) {
            showResult(csvwProcessor.processMetadata(settings, bean.getMetadataUrl()));
        } else if (isCsvUrl) {
            showResult(csvwProcessor.processTabularData(settings, bean.getCsvUrl()));
        } else if (isTabularUpload) {
            //showResult(csvwProcessor.processTabularData(settings, tabularDataFile, tabularDataFileName));
        } else if (isMetadataUpload) {
            //showResult(csvwProcessor.processMetadata(settings, metadataFile, metadataFileName));
        } else {
            Notification.show("Insert some files!");
        }
    }

    private void showResult(ProcessingResult result) {
        VerticalLayout resultDiv = new VerticalLayout();
        resultDiv.setSpacing(true);

        LocalizedButton csvButton = new LocalizedButton("result.csvResults");
        FileDownloadWrapper csvButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.csv", () -> new ByteArrayInputStream(csvResultWriter.writeResult(result))));
        csvButtonWrapper.wrapComponent(csvButton);
        LocalizedButton rdfButton = new LocalizedButton("result.rdfResults");
        FileDownloadWrapper rdfButtonWrapper = new FileDownloadWrapper(
                new StreamResource("result.ttl", () -> new ByteArrayInputStream(rdfResultWriter.writeResult(result))));
        rdfButtonWrapper.wrapComponent(rdfButton);

        LocalizedParamLabel resultLabel = new LocalizedParamLabel("result.status", result.getValidationStatus().name());
        resultDiv.add(resultLabel);
        if (result.getTabularUrl() != null) {
            LocalizedParamLabel csvLabel = new LocalizedParamLabel("result.csvUrl", result.getTabularUrl());
            resultDiv.add(csvLabel);
        }
        if (result.getMetadataUrl() != null) {
            LocalizedParamLabel metadataLabel = new LocalizedParamLabel("result.metadataUrl", result.getMetadataUrl());
            resultDiv.add(metadataLabel);
        }
        resultDiv.add(new HorizontalLayout(csvButtonWrapper, rdfButtonWrapper));
        List<ValidationError> items = new ArrayList<>(result.getErrors());
        if (!items.isEmpty()) {
            Grid<ValidationError> grid = new Grid<>(ValidationError.class);
            grid.setItems(items);
            grid.setColumns("severity", "formattedMessage");
            grid.getColumns().get(0).setResizable(true).setHeader("result.grid.severity");
            grid.getColumns().get(1).setFlexGrow(2).setHeader("result.grid.message");
            grid.setHeightByRows(true);
            resultDiv.add(grid);
        }
        add(resultDiv);
    }

}
