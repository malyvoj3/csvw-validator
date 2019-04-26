package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.result.CsvResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.RdfResultWriter;
import com.malyvoj3.csvwvalidator.web.view.components.LabeledProgressBar;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedButton;
import com.malyvoj3.csvwvalidator.web.view.components.LocalizedLabel;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@HtmlImport("styles/validator-styles.html")
@Route(value = "result", layout = CsvwLayout.class)
@PageTitle("Result")
public class ResultView extends MainLayout {

    private final CsvwProcessor csvwProcessor;
    private final CsvResultWriter csvResultWriter;
    private final RdfResultWriter rdfResultWriter;
    private Thread validationThread;
    private LabeledProgressBar progressBar;

    public ResultView(@Autowired CsvwProcessor csvwProcessor,
                      @Autowired CsvResultWriter csvResultWriter,
                      @Autowired RdfResultWriter rdfResultWriter) {
        this.csvwProcessor = csvwProcessor;
        this.csvResultWriter = csvResultWriter;
        this.rdfResultWriter = rdfResultWriter;

        add(createHeader("header.result"));
        ValidatingDataDTO bean = ComponentUtil.getData(UI.getCurrent(), ValidatingDataDTO.class);
        if (bean != null) {
            progressBar = new LabeledProgressBar("result.waiting");
            add(progressBar);
        } else {
            buildEmptyResult();
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        ValidatingDataDTO bean = ComponentUtil.getData(UI.getCurrent(), ValidatingDataDTO.class);
        if (bean != null) {
            ComponentUtil.setData(UI.getCurrent(), ValidatingDataDTO.class, null);
            validationThread = new
                    ValidationThread(attachEvent.getUI(), this, bean, csvwProcessor, csvResultWriter, rdfResultWriter);
            validationThread.start();
        }
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        // Cleanup
        if (validationThread != null) {
            validationThread.interrupt();
            validationThread = null;
        }
    }

    public void clearProgressBar() {
        if (progressBar != null) {
            remove(progressBar);
            progressBar = null;
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

}
