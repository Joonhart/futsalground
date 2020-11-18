package com.futsalground.portfolio.ground.domain;

import com.futsalground.portfolio.common.domain.BaseEntity;
import com.futsalground.portfolio.member.domain.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@SequenceGenerator(
        name = "reservation_seq_generator",
        sequenceName = "reservation_seq", allocationSize = 1)
@Table(name = "t_reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reservation_seq_generator")
    @Column(name = "reservation_id")
    private Long id;

    private String email;

    private String grdName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate revDate;
    private String revTime;
    private int cost;

    private char payMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ground_id")
    private Ground ground;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Reservation(Long id, String email, Member member, Ground ground, LocalDate revDate, String revTime, int cost, char payMethod) {
        this.id = id;
        this.email = email;
        this.member = member;
        this.ground = ground;
        this.revDate = revDate;
        this.revTime = revTime;
        this.cost = cost;
        this.payMethod = payMethod;
    }
}
