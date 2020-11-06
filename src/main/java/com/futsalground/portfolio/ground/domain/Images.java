package com.futsalground.portfolio.ground.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Images {

    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;

    protected Images() {

    }

    public Images(String img1, String img2, String img3, String img4, String img5) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
    }
}
