package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecruitApplyCustomRepositoryImpl implements RecruitApplyCustomRepository {

    private final EntityManager em;

    @Override
    public List<MyApplyShowDto> findRecruitOfApply(Member member) {
        List<ApplyMember> applyMembers = em.createQuery("select a from ApplyMember a where a.applicant = :member", ApplyMember.class)
                .setParameter("member", member)
                .getResultList();
        List<MyApplyShowDto> resultList = new ArrayList<>();
        applyMembers.stream().map(applyMember -> new MyApplyShowDto(
                applyMember.getRecruit().getId(),
                applyMember.getRecruit().getMatchInfo().getGroundname(),
                applyMember.getRecruit().getMatchInfo().getStarttime(),
                applyMember.getRecruit().getTeamInfo().getPhone(),
                applyMember.isSelected()
        ));

        return resultList;
    }
}