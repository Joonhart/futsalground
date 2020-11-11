package com.futsalground.portfolio.player.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RecruitPageViewDto {

    private Long id;
    private String groundname;
    private String addr1;
    private String addr2;
    private LocalDateTime starttime;
    int volume;

    public RecruitPageViewDto(Long id, String groundname, String addr1, String addr2, LocalDateTime starttime, int volume) {
        this.id = id;
        this.groundname = groundname;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.starttime = starttime;
        this.volume = volume;
    }
}
