package com.futsalground.portfolio.player.domain;

import com.futsalground.portfolio.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "applyMember_seq_generator",
        sequenceName = "applyMember_seq", allocationSize = 1)
@Table(name = "t_apply_member")
public class ApplyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applyMember_seq_generator")
    @Column(name = "apply_member_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member applicant;
}
