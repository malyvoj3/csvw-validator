package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.progressbar.ProgressBar;

public class LabeledProgressBar extends Div {

    private ProgressBar progressBar;
    private LocalizedSpan label;

    public LabeledProgressBar(String propertyName) {
        progressBar = new ProgressBar();
        progressBar.setIndeterminate(true);
        label = new LocalizedSpan(propertyName);
        label.setClassName("progressBar-label");
        add(label);
        add(progressBar);
        setClassName("progressBar-div");
    }

}
