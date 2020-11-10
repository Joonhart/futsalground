package com.futsalground.portfolio.player.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "recruit_seq_generator",
        sequenceName = "recruit_seq", allocationSize = 1)
@ToString
@Table(name = "t_recruit")
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recruit_seq_generator")
    Long id;

    @Embedded
    TeamInfo teamInfo;
    @Embedded
    MatchInfo matchInfo;

    int volume;
    int apply;

    String explanation;

    @Builder
    public Recruit(Long id, TeamInfo teamInfo, MatchInfo matchInfo, int volume, int apply, String explanation) {
        this.id = id;
        this.teamInfo = teamInfo;
        this.matchInfo = matchInfo;
        this.volume = volume;
        this.apply = apply;
        this.explanation = explanation;
    }
}
