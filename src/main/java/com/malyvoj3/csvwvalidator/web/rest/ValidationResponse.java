package com.malyvoj3.csvwvalidator.web.rest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResponse {

    private List<String> validationErrors = new ArrayList<>();

}
