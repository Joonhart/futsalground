package com.futsalground.portfolio.member.controller;

import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import com.futsalground.portfolio.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberControllerTest {

    private final MemberService memberService;

    public MemberControllerTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    public void memberJoin() {
        MemberSaveDto memberSaveDto = new MemberSaveDto(null, "joonhart@gmail.com", "1234");
        memberService.save(memberSaveDto);

        MemberViewDto member = memberService.findMember(1L).get();
        assertThat(memberSaveDto.getId()).isEqualTo(member.getId());
        assertThat(memberSaveDto.getEmail()).isEqualTo(member.getEmail());
    }
}