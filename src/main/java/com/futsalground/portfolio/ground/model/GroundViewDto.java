package com.futsalground.portfolio.ground.model;

import com.futsalground.portfolio.ground.domain.Amenities;
import com.futsalground.portfolio.ground.domain.GroundInfo;
import com.futsalground.portfolio.ground.domain.Images;
import com.futsalground.portfolio.ground.domain.TimeAndCost;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;

@Getter
@Setter
@ToString
public class GroundViewDto {

    private String grdName;
    private String name;
    private String phone;
    private String roadAddr;
    private String numAddr;
    private TimeAndCost timeAndCost;
    private char matchtype;
    private Amenities amenities;
    private Images images;
    private int size1;
    private int size2;
    private GroundInfo groundInfo;

    public GroundViewDto(String grdName, String name, String phone, String roadAddr, String numAddr, TimeAndCost timeAndCost, char matchtype, Amenities amenities, Images images, int size1, int size2, GroundInfo groundInfo) {
        this.grdName = grdName;
        this.name = name;
        this.phone = phone;
        this.roadAddr = roadAddr;
        this.numAddr = numAddr;
        this.timeAndCost = timeAndCost;
        this.matchtype = matchtype;
        this.amenities = amenities;
        this.images = images;
        this.size1 = size1;
        this.size2 = size2;
        this.groundInfo = groundInfo;
    }
}
