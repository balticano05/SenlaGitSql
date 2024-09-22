package com.online.shop.service;

import com.online.shop.utils.StringConst;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {
    public boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(StringConst.DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
