package com.futsalground.portfolio.member.controller;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import com.futsalground.portfolio.member.repository.MemberRepository;
import com.futsalground.portfolio.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberControllerTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
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

    @Test
    @DisplayName("회원 비밀번호 변경")
    public void changePwd() {
        // given
        String email = "joonhart3@gmail.com";
        String pwd = "1234";
        MemberSaveDto memberSaveDto = new MemberSaveDto(null, email, pwd, "성남시", "분당구", "공격수");
        Long saveNum = memberService.save(memberSaveDto);

        em.flush();
        em.clear();

        // when
        String newPwd = "5678";

        // then
        memberService.changePW(email, newPwd);
        Member member = memberRepository.findById(saveNum).get();
        assertThat(member.getPassword()).isEqualTo(newPwd);
    }
}