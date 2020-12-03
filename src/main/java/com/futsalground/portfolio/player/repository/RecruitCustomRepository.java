package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.MyRecruitDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitCustomRepository {

    List<MyApplyShowDto> findRecruitOfApply(Member member);

    List<MyRecruitDto> findMyRecruits(Member member);

    List<ApplyMember> findApplyMembers(Long id);

    Page<Recruit> findRecruting(Pageable pageable);

    Page<Recruit> findByAddr1Containing(String addr, Pageable pageable);

    Page<Recruit> findByGrdNameContaining(String grdName, Pageable pageable);
}
