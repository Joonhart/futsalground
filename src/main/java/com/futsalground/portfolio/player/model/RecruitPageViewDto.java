package com.futsalground.portfolio.player.model;

import com.futsalground.portfolio.player.domain.MatchInfo;
import com.futsalground.portfolio.player.domain.TeamInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RecruitPageViewDto {

    String groundname;
    String addr1;
    String addr2;
    LocalDateTime starttime;
    int volume;

    public RecruitPageViewDto(String groundname, String addr1, String addr2, LocalDateTime starttime, int volume) {
        this.groundname = groundname;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.starttime = starttime;
        this.volume = volume;
    }
}
