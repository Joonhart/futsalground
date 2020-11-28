package com.futsalground.portfolio.player.controller;

import com.futsalground.portfolio.exception.RecruitNotFoundException;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.MatchInfo;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.domain.TeamInfo;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import com.futsalground.portfolio.player.repository.RecruitCustomRepository;
import com.futsalground.portfolio.player.repository.RecruitApplyRepository;
import com.futsalground.portfolio.player.service.RecruitService;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RecruitControllerTest {

    @Autowired
    RecruitService recruitService;
    @Autowired
    RecruitApplyRepository recruitApplyRepository;
    @Autowired
    RecruitCustomRepository recruitCustomRepository;
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

        assertThat(recruitDto.getApplicants().size()).isEqualTo(1);
    }

    private RecruitDto recruitCreate() {
        RecruitDto recruitDto = new RecruitDto(null, new Member(null, null, null, null, null, null, null, null, null, null), "깨비깨비", "25~30", "g, d, m", "중", "카카오톡",
                "hihi", "보정동풋살장", "기흥구", "보정동", null, "2020-11-11(수)", "14:00", 10000
                , 2, 0, "와서 재밌게 재주껏 하시길", 0);

        String day = recruitDto.getDay();
        String time = recruitDto.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(day.substring(0, day.length() - 3) + " " + time + ":00.000", formatter);
        recruitDto.setStarttime(dateTime);
        recruitService.create(recruitDto);
        return recruitDto;
    }

    @Test
    @DisplayName("용병 게시판에 예약하고 대기중인 상태 표시")
    public void recruitApplyTest() throws RecruitNotFoundException {
        Member member1 = new Member(null, null, null, null, 1L, "joonhart1@gmail.com", "1234", "addr1", "addr2", "gd");
        Member member2 = new Member(null, null, null, null, 1L, "joonhart2@gmail.com", "1234", "addr1", "addr2", "gd");

        new Recruit(1L, member1,
                new TeamInfo("abc", "a", "20", "skill", "kakao", "1234"),
                new MatchInfo("groundName", "addr1", "addr2", LocalDateTime.now(), 1000),
                3, 3, "하이루", null);

        recruitService.apply(1L, member2);

        List<MyApplyShowDto> myApplys = recruitService.findMyApplys(member2);
        System.out.println("myApplys.size() = " + myApplys.size());
        for (MyApplyShowDto myApply : myApplys) {
            System.out.println("myApply = " + myApply);
        }
    }
}