package com.futsalground.portfolio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

    public static void main(String[] args) {

        LocalDateTime today = LocalDateTime.now();

        System.out.println("today = " + today);
        System.out.println(today.plusDays(1));

        String formatDate1 = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd(E)"));
        String formatDate2 = today.format(DateTimeFormatter.ofPattern("HH:mm"));

        System.out.println("formatDate1 = " + formatDate1);
        System.out.println("formatDate2 = " + formatDate2);

        String day = "2020-11-18(수)";
        String time = "12:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(day.substring(0, day.length()-3) + " " + time + ":00.000", formatter);
        System.out.println("dateTime = " + dateTime);

        String position = "g,d";
        if (position.contains("g")) {
            System.out.print("골키퍼 ");
        }
        if (position.contains("d")) {
            System.out.print("수비수 ");
        }

        LocalDate date = LocalDate.now();
        System.out.println("date = " + date);
    }
}
