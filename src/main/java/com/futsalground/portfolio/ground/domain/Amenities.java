package com.futsalground.portfolio.ground.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Amenities {

    private char parking;
    private char hotcold;
    private char ball;
    private char vest;
    private char shoes;
    private char shower;

    protected Amenities() {

    }

    public Amenities(char parking, char hotcold, char ball, char vest, char shoes, char shower) {
        this.parking = parking;
        this.hotcold = hotcold;
        this.ball = ball;
        this.vest = vest;
        this.shoes = shoes;
        this.shower = shower;
    }
}
