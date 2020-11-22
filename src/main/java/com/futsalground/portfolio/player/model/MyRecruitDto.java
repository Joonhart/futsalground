package com.futsalground.portfolio.player.model;

import com.futsalground.portfolio.player.domain.ApplyMember;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class MyRecruitDto {

    private String grdName;
    private LocalDateTime gameTime;
    private int volume;
    private int applyCnt;
    private List<ApplyMember> applyMembers;

    public MyRecruitDto(String grdName, LocalDateTime gameTime, int volume, int applyCnt, List<ApplyMember> applyMembers) {
        this.grdName = grdName;
        this.gameTime = gameTime;
        this.volume = volume;
        this.applyCnt = applyCnt;
        this.applyMembers = applyMembers;
    }
}