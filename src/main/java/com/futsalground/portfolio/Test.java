package com.futsalground.portfolio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("today = " + today.toLocalDate());
        System.out.println(today.toLocalDate().isAfter(date));
        System.out.println(today.toLocalDate().isBefore(date));
        System.out.println(today.toLocalDate().isEqual(date));

        List<String> result = new ArrayList<>();
        result.add("14~16  ");
        result.add("20~22  22~24  ");

        System.out.println("result = " + result);

        // resultList = [14~16  , 20~22  22~24  ]
        // resultList = [14~16  , 20~22  22~24  ]

        System.out.println("result.size() = " + result.size());

        ArrayList<String> resultList = new ArrayList<>();

        for (String s : result) {
            System.out.println("s ================ " + s);
            String[] s1 = s.split(" ");
            for (String s2 : s1) {
                s2.trim();
                if (!s2.equals("") && s2 != null) {
                    resultList.add(s2);
                    System.out.println("s2 = " + s2);
                }
            }
        }
        System.out.println("resultList = " + resultList);


        String day2 = "2020-11-18(수)";

        LocalDate localDate = LocalDate.parse(day2.substring(0, day2.length()-3));
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.now());
        System.out.println("localDate = " + localDateTime);
    }
}
