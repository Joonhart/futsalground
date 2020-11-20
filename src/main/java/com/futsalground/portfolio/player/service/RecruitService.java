package com.futsalground.portfolio.player.service;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RecruitService {

    Optional<RecruitDto> findById(Long id);

    void create(RecruitDto recruitDto);

    Page<RecruitPageViewDto> findAll(Pageable pageable);

    void apply(Long id, Member member);

    List<MyApplyShowDto> findMyApplys(Member member);

//    Page<RecruitPageViewDto> findByAddr1(String addr1, Pageable pageable);
//
//    Page<RecruitPageViewDto> findByGroundname(String groundname, Pageable pageable);
}
