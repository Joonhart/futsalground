package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.MyRecruitDto;

import java.awt.print.Pageable;
import java.util.List;

public interface RecruitCustomRepository {

    List<MyApplyShowDto> findRecruitOfApply(Member member);

    List<MyRecruitDto> findMyRecruits(Member member);
}
