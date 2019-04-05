package com.malyvoj3.csvwvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ZonedTime {

    private LocalTime time;

    // Zone offset in seconds
    private int zoneOffset;

}
