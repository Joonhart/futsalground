package com.futsalground.portfolio.player.domain;

import com.futsalground.portfolio.member.domain.Member;
import lombok.*;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @Column(name = "recruit_id")
    private Long id;

    @Embedded
    private TeamInfo teamInfo;
    @Embedded
    private MatchInfo matchInfo;

    private int volume;
    private int apply;

    private String explanation;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member recruitMember;

    @OneToMany(mappedBy = "recruit")
    private List<ApplyMember> applyMembers = new ArrayList<>();
    @OneToMany(mappedBy = "selected")
    private List<SelectMember> selectMembers = new ArrayList<>();

    @Builder
    public Recruit(Long id, Member recruitMember, TeamInfo teamInfo, MatchInfo matchInfo, int volume, int apply, String explanation, List<ApplyMember> applyMembers, List<SelectMember> selectMembers) {
        this.id = id;
        this.recruitMember = recruitMember;
        this.teamInfo = teamInfo;
        this.matchInfo = matchInfo;
        this.volume = volume;
        this.apply = apply;
        this.explanation = explanation;
        this.applyMembers = applyMembers;
        this.selectMembers = selectMembers;
    }

//    public void addApplyMember(Member member) {
//        this.applyMembers.add(member);
//    }
}