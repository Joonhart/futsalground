package com.futsalground.portfolio.player.model;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IdDto {

    private Long id;
    private Member member;
}
