package com.futsalground.portfolio.player.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchInfo {

    String groundname;
    String addr1;
    String addr2;
    LocalDateTime starttime;
    int cost;

    @Builder
    public MatchInfo(String groundname, String addr1, String addr2, LocalDateTime starttime, int cost) {
        this.groundname = groundname;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.starttime = starttime;
        this.cost = cost;
    }
}
