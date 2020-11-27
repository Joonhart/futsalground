package com.futsalground.portfolio.ground.model;

import com.futsalground.portfolio.ground.domain.Amenities;
import com.futsalground.portfolio.ground.domain.GroundInfo;
import com.futsalground.portfolio.ground.domain.Images;
import com.futsalground.portfolio.ground.domain.TimeAndCost;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GroundViewDto {

    private Long id;
    private String grdName;
    private String name;
    private String phone;
    private String roadAddr;
    private String numAddr;
    private TimeAndCost timeAndCost;
    private String matchtype;
    private Amenities amenities;
    private Images images;
    private int size1;
    private int size2;
    private GroundInfo groundInfo;
    private LocalDate nowDate;
    private int nowTime;
    private List<String> revs = new ArrayList<>();

    public GroundViewDto(Long id, String grdName, String name, String phone, String roadAddr, String numAddr, TimeAndCost timeAndCost, String matchtype, Amenities amenities, Images images, int size1, int size2, GroundInfo groundInfo, LocalDate nowDate, int nowTime, List<String> revs) {
        this.id = id;
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
        this.nowDate = nowDate;
        this.nowTime = nowTime;
        this.revs = revs;
    }
}
