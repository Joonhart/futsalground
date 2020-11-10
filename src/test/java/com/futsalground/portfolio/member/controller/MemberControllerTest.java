package com.futsalground.portfolio.member.controller;

import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import com.futsalground.portfolio.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberControllerTest {

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;


    @Test
    @DisplayName("회원가입")
    public void memberJoin() {
        MemberSaveDto memberSaveDto = new MemberSaveDto(null, "joonhart@gmail.com", "1234", "성남시", "분당구", "공격수");
        Long saveNum = memberService.save(memberSaveDto);

        em.flush();
        em.clear();

        MemberViewDto member = memberService.findMember(saveNum).get();
        assertThat(memberSaveDto.getEmail()).isEqualTo(member.getEmail());
        System.out.println("member = " + member);
    }
}