package com.futsalground.portfolio;

import java.time.LocalDateTime;

public class Test {

    public static void main(String[] args) {

        LocalDateTime today = LocalDateTime.now();

        System.out.println("today = " + today);
        System.out.println(today.plusDays(1));
    }
}
