package com.futsalground.portfolio.member.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberViewDto {

    private Long id;
    private String email;

    public MemberViewDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
