package com.online.shop.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Validator {
    public static boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
