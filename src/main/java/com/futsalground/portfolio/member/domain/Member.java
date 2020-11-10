package com.futsalground.portfolio.member.domain;

import com.futsalground.portfolio.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "member_seq", allocationSize = 1)
@ToString
@Table(name = "t_member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

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
    private int spend;

    @Builder
    public Member(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate, Long id, String email, String password, String addr1, String addr2, String position) {
        super(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
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
        this.spend = 0;
    }
}
