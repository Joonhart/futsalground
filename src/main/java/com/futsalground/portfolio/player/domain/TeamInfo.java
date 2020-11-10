package com.futsalground.portfolio.player.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class TeamInfo {

    String teamname;
    String position;
    String ages;
    String skill;
    String contactway;
    String phone;

    @Builder
    public TeamInfo(String teamname, String position, String ages, String skill, String contactway, String phone) {
        this.teamname = teamname;
        this.position = position;
        this.ages = ages;
        this.skill = skill;
        this.contactway = contactway;
        this.phone = phone;
    }
}
