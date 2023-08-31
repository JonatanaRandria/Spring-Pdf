package com.example.prog4.controller.view;

import com.example.prog4.model.enums.YearEnum;

import java.time.LocalDate;
import java.time.Period;


public class YearUtils {

    public static String getAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return period.getYears() + " ans";
    }

    public static String getAgeByEnum(YearEnum yearEnum, LocalDate date) {
        if (yearEnum == YearEnum.BIRTHDAY) {
            return getAge(date);
        }
        if (yearEnum == YearEnum.YEAR_ONlY) {
            LocalDate date1 = LocalDate.now();
            int yearDate = date.getYear() - date1.getYear();
            return String.valueOf(yearDate);

        }
        return "CUSTOM_YEARS";
    }
}