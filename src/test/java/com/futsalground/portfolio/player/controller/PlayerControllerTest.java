package com.futsalground.portfolio.player.controller;

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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlayerControllerTest {

    @Autowired
    RecruitService recruitService;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("용병구하기 게시글 저장 Test")
    public void recruitPost() {
        RecruitDto recruitDto = new RecruitDto(null, "깨비깨비", "골키퍼", "25~30", "중", "카카오톡",
                "hihi", "보정동풋살장", "기흥구", "보정동", LocalDateTime.now(), 10000
                , 2, 0, "와서 재밌게 재주껏 하시길");

        recruitService.create(recruitDto);

        em.flush();
        em.clear();

        Page<RecruitPageViewDto> all = recruitService.findAll(Pageable.unpaged());
        Optional<RecruitDto> findById = recruitService.findById(1L);
        for (RecruitPageViewDto recruitPageViewDto : all) {
            System.out.println("recruitPageViewDto = " + recruitPageViewDto);
        }
        System.out.println("all.getTotalElements() = " + all.getTotalElements());

        assertThat(all.getContent().size()).isEqualTo(1);
        System.out.println("findById = " + findById.get());
    }
}