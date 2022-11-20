package br.com.tiagopedroso.dioparkingchallenge.tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeConverter {

    private DateTimeConverter() {
        super();
    }

    public static Date toDate(LocalDate date){
        return Date.from(
                date.atStartOfDay(
                        ZoneId.systemDefault()
                ).toInstant()
        );
    }

    public static LocalDate toLocalDate(Date date) {
        return date
                .toInstant()
                .atZone(
                        ZoneId.systemDefault()
                ).toLocalDate();
    }

    public static Date toDate(LocalDateTime dateTime){
        return Date.from(
                dateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date
                .toInstant()
                .atZone(
                        ZoneId.systemDefault()
                ).toLocalDateTime();
    }



}
