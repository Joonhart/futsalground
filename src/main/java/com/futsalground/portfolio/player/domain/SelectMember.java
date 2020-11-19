package com.futsalground.portfolio.player.domain;


import com.futsalground.portfolio.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "selectMember_seq_generator",
        sequenceName = "selectMember_seq", allocationSize = 1)
@Table(name = "t_select_member")
public class SelectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "selectMember_seq_generator")
    @Column(name = "select_member_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member selected;
}
