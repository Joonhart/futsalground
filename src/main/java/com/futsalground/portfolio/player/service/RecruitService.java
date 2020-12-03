package com.futsalground.portfolio.player.service;

import com.futsalground.portfolio.exception.ApplyMemberNotFoundException;
import com.futsalground.portfolio.exception.RecruitNotFoundException;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.MyRecruitDto;
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

    Page<RecruitPageViewDto> findRecruting(Pageable pageable);

    void apply(Long id, Member member) throws RecruitNotFoundException;

    List<MyApplyShowDto> findMyApplys(Member member);

    List<MyRecruitDto> findMyRecruit(Member member);

    void removeApply(Long id);

    void recruitSelect(Long id) throws ApplyMemberNotFoundException;

    void updateRecruit(Long id, RecruitDto recruitDto) throws RecruitNotFoundException;

    List<ApplyMember> getApplyMemeber(Long id);

    void delete(Long id);

    Page<RecruitPageViewDto> findByAddr(Pageable pageable, String addr);

    Page<RecruitPageViewDto> findByGrdName(Pageable pageable, String grdName);
}
