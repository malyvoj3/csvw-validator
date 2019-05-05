package com.malyvoj3.csvwvalidator.persistance.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ProcessingSettings {

    private Boolean strictMode;

}
