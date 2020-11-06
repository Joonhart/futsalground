package com.futsalground.portfolio.ground.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class GroundInfo {

    private char floor;
    private char water;
    private char indoor;
    private String rule;

    protected GroundInfo() {

    }

    public GroundInfo(char floor, char water, char indoor, String rule) {
        this.floor = floor;
        this.water = water;
        this.indoor = indoor;
        this.rule = rule;
    }
}
