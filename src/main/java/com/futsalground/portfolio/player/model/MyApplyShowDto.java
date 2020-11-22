package com.futsalground.portfolio.player.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MyApplyShowDto {

    private Long recruitMemberId;
    private Long recruitId;
    private String grdName;
    private LocalDateTime starttime;
    private String phone;
    private boolean selected;

    public MyApplyShowDto(Long recruitMemberId, Long recruitId, String grdName, LocalDateTime starttime, String phone, boolean selected) {
        this.recruitMemberId = recruitMemberId;
        this.recruitId = recruitId;
        this.grdName = grdName;
        this.starttime = starttime;
        this.phone = phone;
        this.selected = selected;
    }
}
