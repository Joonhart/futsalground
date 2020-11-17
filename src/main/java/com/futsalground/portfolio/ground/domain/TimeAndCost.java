package com.futsalground.portfolio.ground.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class TimeAndCost {

    private String openday;
    private String startTime;
    private int cost;

    protected TimeAndCost() {
    }

    public TimeAndCost(String openday, String startTime, int cost) {
        this.openday = openday;
        this.startTime = startTime;
        this.cost = cost;
    }
}
