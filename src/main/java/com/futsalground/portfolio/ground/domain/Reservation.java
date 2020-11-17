package com.futsalground.portfolio.ground.domain;

import com.futsalground.portfolio.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String revDate;
    private String revTime;
    private int cost;

    private char payMethod;

    @Builder
    public Reservation(Long id, String email, String grdName, String revDate, String revTime, int cost, char payMethod) {
        this.id = id;
        this.email = email;
        this.grdName = grdName;
        this.revDate = revDate;
        this.revTime = revTime;
        this.cost = cost;
        this.payMethod = payMethod;
    }

    public Reservation(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate, Long id, String email, String grdName, String revDate, String revTime, int cost, char payMethod) {
        super(email, createdDate, email, lastModifiedDate);
        this.id = id;
        this.email = email;
        this.grdName = grdName;
        this.revDate = revDate;
        this.revTime = revTime;
        this.cost = cost;
        this.payMethod = payMethod;
    }
}
