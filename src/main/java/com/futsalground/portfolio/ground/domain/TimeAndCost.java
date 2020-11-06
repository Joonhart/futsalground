package com.futsalground.portfolio.ground.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class TimeAndCost {

    private String wdslt;
    private String weslt;
    private String wdTime;
    private String weTime;
    private int wdCost;
    private int weCost;

    protected TimeAndCost() {
    }

    public TimeAndCost(String wdslt, String weslt, String wdTime, String weTime, int wdCost, int weCost) {
        this.wdslt = wdslt;
        this.weslt = weslt;
        this.wdTime = wdTime;
        this.weTime = weTime;
        this.wdCost = wdCost;
        this.weCost = weCost;
    }
}
