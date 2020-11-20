package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.MyApplyShowDto;

import java.awt.print.Pageable;
import java.util.List;

public interface RecruitApplyCustomRepository {

    List<MyApplyShowDto> findRecruitOfApply(Member member);
}
