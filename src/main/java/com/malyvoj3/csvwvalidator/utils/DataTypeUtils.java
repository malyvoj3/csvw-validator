package com.malyvoj3.csvwvalidator.utils;

import com.malyvoj3.csvwvalidator.domain.ZonedDate;
import com.malyvoj3.csvwvalidator.domain.ZonedTime;
import lombok.experimental.UtilityClass;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class DataTypeUtils {

    public ZonedDateTime parseDateTime(String pattern, String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        TemporalAccessor temporalAccessor = formatter.parse(dateTimeString);
        if (temporalAccessor.query(TemporalQueries.zone()) == null) {
            temporalAccessor = formatter.withZone(ZoneId.systemDefault()).parse(dateTimeString);
        }
        return ZonedDateTime.from(temporalAccessor);
    }

    public ZonedTime parseTime(String pattern, String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        TemporalAccessor temporalAccessor = formatter.parse(timeString);
        LocalTime localTime = LocalTime.from(formatter.parse(timeString));
        ZoneOffset zoneOffset = temporalAccessor.query(TemporalQueries.offset());
        int offsetSeconds = zoneOffset != null ? zoneOffset.getTotalSeconds() : 0;
        return new ZonedTime(localTime, offsetSeconds);
    }

    public ZonedDate parseDate(String pattern, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        TemporalAccessor temporalAccessor = formatter.parse(dateString);
        LocalDate localDate = LocalDate.from(formatter.parse(dateString));
        ZoneOffset zoneOffset = temporalAccessor.query(TemporalQueries.offset());
        int offsetSeconds = zoneOffset != null ? zoneOffset.getTotalSeconds() : 0;
        return new ZonedDate(localDate, offsetSeconds);
    }

    public Duration parseDuration(String durationString) {
        Duration duration = null;
        try {
            duration = DatatypeFactory.newInstance().newDuration(durationString);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalArgumentException();
        }
        return duration;
    }

    public boolean matchFormat(String validatedString, String format) {
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(format);
        return matcher.matches();
    }

}
