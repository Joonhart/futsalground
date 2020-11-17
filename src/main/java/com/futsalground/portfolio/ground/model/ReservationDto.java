package com.futsalground.portfolio.ground.model;

import com.futsalground.portfolio.ground.domain.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationDto {

    // 예약자 정보
    private String email;

    // 대관 예약 정보
    private String grdName;
    private String revDate;
    private String revTime;
    private int cost;

    private char payMethod;

    public Reservation toEntity(ReservationDto reservationDto) {
        return Reservation.builder()
                .id(null)
                .email(email)
                .grdName(grdName)
                .revDate(revDate)
                .revTime(revTime)
                .cost(cost)
                .payMethod(payMethod)
                .build();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReservationDto(String email, String grdName, String revDate, String revTime, int cost, char payMethod) {
        this.email = email;
        this.grdName = grdName;
        this.revDate = revDate;
        this.revTime = revTime;
        this.cost = cost;
        this.payMethod = payMethod;
    }
}
