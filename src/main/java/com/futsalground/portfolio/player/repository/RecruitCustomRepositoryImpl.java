package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.MyRecruitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecruitCustomRepositoryImpl implements RecruitCustomRepository {

    private final EntityManager em;

    @Override
    public List<MyApplyShowDto> findRecruitOfApply(Member member) {
        List<ApplyMember> applyMembers = em.createQuery(
                "select a " +
                        "from ApplyMember a " +
                        "where a.applicant = :member", ApplyMember.class)
                .setParameter("member", member)
                .getResultList();
        List<MyApplyShowDto> resultList = new ArrayList<>();
        for (ApplyMember applyMember : applyMembers) {
            resultList.add(new MyApplyShowDto(
                    applyMember.getId(),
                    applyMember.getRecruit().getId(),
                    applyMember.getRecruit().getMatchInfo().getGroundname(),
                    applyMember.getRecruit().getMatchInfo().getStarttime(),
                    applyMember.getRecruit().getTeamInfo().getPhone(),
                    applyMember.isSelected()
            ));
        }

        return resultList;
    }

    @Override
    public List<MyRecruitDto> findMyRecruits(Member member) {
        List<Recruit> recruits = em.createQuery("select r" +
                " from Recruit r" +
                " where r.recruitMember = :member", Recruit.class)
                .setParameter("member", member)
                .getResultList();
        List<MyRecruitDto> result = new ArrayList<>();
        for (Recruit recruit : recruits) {
            result.add(new MyRecruitDto(
                    recruit.getMatchInfo().getGroundname(),
                    recruit.getMatchInfo().getStarttime(),
                    recruit.getVolume(),
                    recruit.getApplyMembers().size(),
                    recruit.getApplyMembers()));
        }
        return result;
    }
}