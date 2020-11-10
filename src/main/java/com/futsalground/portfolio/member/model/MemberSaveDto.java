package com.futsalground.portfolio.member.model;

import com.futsalground.portfolio.member.domain.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberSaveDto {

    private Long id;
    private String email;
    private String password;

    private String addr1;
    private String addr2;

    private String position;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .addr1(addr1)
                .addr2(addr2)
                .position(position)
                .build();
    }

    @Builder
    public MemberSaveDto(Long id, String email, String password, String addr1, String addr2, String position) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.position = position;
    }
}
