package com.futsalground.portfolio.ground.model;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationDto {

    private Long id;

    // 예약자 정보
    private String email;
    private LocalDateTime createdDate;

    // 대관 예약 정보
    private LocalDate revDate;
    private String revTime;
    private int cost;

    private char payMethod;

    private boolean isused;

    private Member member;
    private Ground ground;

    public Reservation toEntity(ReservationDto reservationDto) {
        return Reservation.builder()
                .id(null)
                .email(email)
                .member(member)
                .ground(ground)
                .revDate(revDate)
                .revTime(revTime)
                .cost(cost)
                .payMethod(payMethod)
                .build();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReservationDto(Long id, String email, LocalDateTime createdDate, Member member, Ground ground, LocalDate revDate, String revTime, int cost, char payMethod, boolean isused) {
        this.id = id;
        this.email = email;
        this.createdDate = createdDate;
        this.member = member;
        this.ground = ground;
        this.revDate = revDate;
        this.revTime = revTime;
        this.cost = cost;
        this.payMethod = payMethod;
        this.isused = isused;
    }
}
