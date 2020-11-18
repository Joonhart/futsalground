package com.futsalground.portfolio.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.futsalground.portfolio.common.domain.BaseEntity;
import com.futsalground.portfolio.ground.domain.Reservation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "member_seq", allocationSize = 1)
@Table(name = "t_member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    @Column(name = "member_id")
    private Long id;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "membertype")
    private Role membertype;

    private String addr1;
    private String addr2;

    private String position;

    private int boardcnt;
    private int boardreplycnt;

    private int recruitcnt;
    private int applycnt;
    private int revcnt;
    private int spend;

    @Builder
    public Member(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate, Long id, String email, String password, String addr1, String addr2, String position) {

        super(email, createdDate, email, lastModifiedDate);
        this.id = id;
        this.email = email;
        this.password = password;
        this.membertype = Role.MEMBER;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.position = position;
        this.boardcnt = 0;
        this.boardreplycnt = 0;
        this.recruitcnt = 0;
        this.applycnt = 0;
        this.revcnt = 0;
        this.spend = 0;
    }

    public void update(String addr1, String addr2, String position) {
        super.update(LocalDateTime.now());
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.position = position;
    }

    public void changePW(String newPW) {
        this.password = newPW;
    }

    public void PlusBoardCnt() {
        this.boardcnt = this.boardcnt + 1;
    }

    public void PlusRev(int cost) {
        this.spend += cost;
        this.revcnt = this.revcnt + 1;
    }
}
