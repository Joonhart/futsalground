package com.futsalground.portfolio.player.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
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
    Long id;

    String position;
    String locate;
    LocalDate wantdate;
    String ages;
    String skill;
    String contactway;
    String phone;
    String explanation;
}
