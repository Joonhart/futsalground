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
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(length = 100, nullable = false, name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "membertype")
    private Role membertype;

    @Builder
    public Member(Long id, String email, String password) {
        super(email, LocalDateTime.now(), email, LocalDateTime.now());
        this.id = id;
        this.email = email;
        this.password = password;
        this.membertype = Role.MEMBER;
    }
}
