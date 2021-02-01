package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringToLocalTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String formatted, Locale locale) throws ParseException {
        return DateTimeUtil.parseLocalTime(formatted);
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
