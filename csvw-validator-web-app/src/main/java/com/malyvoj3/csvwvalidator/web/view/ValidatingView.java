package com.malyvoj3.csvwvalidator.web.view;

import com.malyvoj3.csvwvalidator.processor.Processor;
import com.malyvoj3.csvwvalidator.processor.result.BatchProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.PersistentResult;
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
@Route(value = "validating", layout = CsvwLayout.class)
@PageTitle("Validating")
public class ValidatingView extends MainLayout {

    private final Processor<PersistentResult, BatchProcessingResult<PersistentResult>> processor;
    private Thread validationThread;
    private LabeledProgressBar progressBar;

    @Autowired
    public ValidatingView(Processor<PersistentResult, BatchProcessingResult<PersistentResult>> processor) {
        this.processor = processor;

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
                    ValidationThread(attachEvent.getUI(), bean, processor);
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
