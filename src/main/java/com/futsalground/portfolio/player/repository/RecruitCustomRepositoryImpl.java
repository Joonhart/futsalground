package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.MyRecruitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
                    recruit.getId(),
                    recruit.getMatchInfo().getGroundname(),
                    recruit.getMatchInfo().getStarttime(),
                    recruit.getVolume(),
                    recruit.getApplyMembers().size(),
                    recruit.getApplyMembers()));
        }
        return result;
    }

    @Override
    public List<ApplyMember> findApplyMembers(Long id) {
        Recruit recruit = em.createQuery("select r" +
                " from Recruit r" +
                " where r.id = :id", Recruit.class)
                .setParameter("id", id)
                .getSingleResult();
        return recruit.getApplyMembers();
    }

    @Override
    public Page<Recruit> findRecruting(Pageable pageable) {
        String sql = "select * from t_recruit where starttime > now()";
        Query nativeQuery = em.createNativeQuery(sql, Recruit.class);
        List<Recruit> recruits = nativeQuery.getResultList();

        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > recruits.size() ? recruits.size() : (start + pageable.getPageSize());
        return new PageImpl<>(recruits.subList(start, end), pageable, recruits.size());
    }

    @Override
    public Page<Recruit> findByAddr1Containing(String addr, Pageable pageable) {
        List<Recruit> recruits = em.createQuery("select r " +
                "from Recruit r " +
                "where r.matchInfo.addr1 like '%" + addr + "%'", Recruit.class)
                .getResultList();

        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > recruits.size() ? recruits.size() : (start + pageable.getPageSize());
        return new PageImpl<>(recruits.subList(start, end), pageable, recruits.size());
    }
    @Override
    public Page<Recruit> findByGrdNameContaining(String grdName, Pageable pageable) {
        List<Recruit> recruits = em.createQuery("select r " +
                "from Recruit r " +
                "where r.matchInfo.groundname like '%" + grdName + "%'", Recruit.class)
                .getResultList();

        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > recruits.size() ? recruits.size() : (start + pageable.getPageSize());
        return new PageImpl<>(recruits.subList(start, end), pageable, recruits.size());
    }
}