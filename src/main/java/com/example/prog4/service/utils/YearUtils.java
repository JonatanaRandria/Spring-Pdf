package com.example.prog4.service.utils;

import java.time.LocalDate;
import java.time.Period;

public class YearUtils {
    public static String getAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return period.getYears()+" âge";
    }

}
