package com.futsalground.portfolio.player.model;

import com.futsalground.portfolio.player.domain.MatchInfo;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.domain.TeamInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RecruitDto {

    private Long id;

    //--teamInfo--//
    private String teamname;
    private String ages;
    private String position;
    private String skill;
    private String contactway;
    private String phone;

    //--matchInfo--//
    private String groundname;
    private String addr1;
    private String addr2;
    private LocalDateTime starttime;
    private String day;
    private String time;

    private int cost;

    private int volume;
    private int apply;
    private String explanation;

    private Set<String> applicants;
    private Set<String> select;

    public Recruit toEntity(RecruitDto recruitDto) {
        return Recruit.builder()
                .id(recruitDto.id)
                .teamInfo(TeamInfo.builder()
                        .teamname(recruitDto.teamname)
                        .position(recruitDto.position)
                        .ages(recruitDto.ages)
                        .contactway(recruitDto.contactway)
                        .phone(recruitDto.phone)
                        .skill(recruitDto.skill)
                        .build())
                .matchInfo(MatchInfo.builder()
                        .addr1(recruitDto.addr1)
                        .addr2(recruitDto.addr2)
                        .groundname(recruitDto.groundname)
                        .starttime(recruitDto.starttime)
                        .cost(recruitDto.cost)
                        .build())
                .volume(recruitDto.volume)
                .apply(recruitDto.apply)
                .explanation(recruitDto.explanation)
                .build();
    }


    public RecruitDto(Long id, String teamname, String ages, String position, String skill, String contactway, String phone, String groundname, String addr1, String addr2, LocalDateTime starttime, String day, String time, int cost, int volume, int apply, String explanation) {
        this.id = id;
        this.teamname = teamname;
        this.ages = ages;
        this.position = position;
        this.skill = skill;
        this.contactway = contactway;
        this.phone = phone;
        this.groundname = groundname;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.starttime = starttime;
        this.day = day;
        this.time = time;
        this.cost = cost;
        this.volume = volume;
        this.apply = apply;
        this.explanation = explanation;
    }

    public void addApply(String applyerEmail) {
        this.applicants.add(applyerEmail);
    }
}
