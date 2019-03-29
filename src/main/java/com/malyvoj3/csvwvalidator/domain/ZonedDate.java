package com.malyvoj3.csvwvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ZonedDate {

    private LocalDate date;

    // Zone offset in seconds
    private int zoneOffset;
}
