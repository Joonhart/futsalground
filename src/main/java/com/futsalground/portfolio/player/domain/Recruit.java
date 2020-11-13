package com.futsalground.portfolio.player.domain;

import com.futsalground.portfolio.member.domain.Member;
import lombok.*;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "recruit_seq_generator",
        sequenceName = "recruit_seq", allocationSize = 1)
@Table(name = "t_recruit")
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recruit_seq_generator")
    @Column(name = "RECRUIT_ID")
    private Long id;

    @Embedded
    private TeamInfo teamInfo;
    @Embedded
    private MatchInfo matchInfo;

    private int volume;
    private int apply;

    private String explanation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member recruitMember;

    @OneToMany
    private Set<Member> applicants = new HashSet<>();

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
