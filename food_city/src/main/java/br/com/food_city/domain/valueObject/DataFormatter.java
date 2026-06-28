package br.com.food_city.domain.valueObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataFormatter {

    private static final DateTimeFormatter DATA =  DateTimeFormatter.ISO_LOCAL_DATE;

    public static LocalDate formatarData(String data) {

        return LocalDate.parse(data, DATA);
    }
}
