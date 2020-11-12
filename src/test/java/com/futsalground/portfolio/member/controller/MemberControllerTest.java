package com.futsalground.portfolio.member.controller;

import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
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
    @DisplayName("회원 정보 업데이트")
    public void memberUpdate() {
        // given
        MemberSaveDto memberSaveDto = new MemberSaveDto(null, "joonhart@gmail.com", "1234", "성남시", "분당구", "공격수");
        Long saveNum = memberService.save(memberSaveDto);

        em.flush();
        em.clear();

        // when
        String email = "joonhart@gmail.com";
        String updateAddr1 = "용인시";
        String updateAddr2 = "기흥구";
        String position = "골키퍼";
        memberService.update(email, updateAddr1, updateAddr2, position);

        em.flush();
        em.clear();

        // then
        MemberViewDto changeMember = memberService.findMember(saveNum).get();
        assertThat(changeMember.getAddr1()).isEqualTo(updateAddr1);
        assertThat(changeMember.getAddr2()).isEqualTo(updateAddr2);
        assertThat(changeMember.getPosition()).isEqualTo(position);
    }
}