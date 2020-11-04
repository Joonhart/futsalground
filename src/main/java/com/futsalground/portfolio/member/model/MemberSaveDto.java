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

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public MemberSaveDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
