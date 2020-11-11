package com.futsalground.portfolio.player.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "apply_seq_generator",
        sequenceName = "apply_seq", allocationSize = 1)
@ToString
@Table(name = "t_apply")
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_seq_generator")
    private Long id;

    private String position;
    private String locate;
    private LocalDateTime wantdate;
    private String ages;
    private String skill;
    private String contactway;
    private String phone;
    private String explanation;

    @Builder
    public Apply(Long id, String position, String locate, LocalDateTime wantdate, String ages, String skill, String contactway, String phone, String explanation) {
        this.id = id;
        this.position = position;
        this.locate = locate;
        this.wantdate = wantdate;
        this.ages = ages;
        this.skill = skill;
        this.contactway = contactway;
        this.phone = phone;
        this.explanation = explanation;
    }
}
