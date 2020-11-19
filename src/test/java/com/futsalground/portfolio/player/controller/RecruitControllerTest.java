package com.futsalground.portfolio.player.controller;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import com.futsalground.portfolio.player.service.RecruitService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecruitControllerTest {

    @Autowired
    RecruitService recruitService;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("용병구하기 게시글 저장 Test")
    public void recruitPost() {
        RecruitDto recruitDto = recruitCreate();

        em.flush();
        em.clear();

        Page<RecruitPageViewDto> all = recruitService.findAll(Pageable.unpaged());
        Optional<RecruitDto> findById = recruitService.findById(1L);
        assertThat(all.getContent().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("용병 게시글에 지원하기 Test")
    public void applyTest() {
        RecruitDto recruitDto = recruitCreate();

        String email = "memberEmail";
        recruitDto.addApply(email);

        assertThat(recruitDto.getApplicants().size()).isEqualTo(1);
    }

    private RecruitDto recruitCreate() {
        RecruitDto recruitDto = new RecruitDto(null, new Member(null, null, null, null, null, null, null, null, null, null), "깨비깨비", "25~30", "g, d, m", "중", "카카오톡",
                "hihi", "보정동풋살장", "기흥구", "보정동", null, "2020-11-11(수)", "14:00", 10000
                , 2, 0, "와서 재밌게 재주껏 하시길");

        String day = recruitDto.getDay();
        String time = recruitDto.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(day.substring(0, day.length() - 3) + " " + time + ":00.000", formatter);
        recruitDto.setStarttime(dateTime);
        recruitService.create(recruitDto);
        return recruitDto;
    }
}