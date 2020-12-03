package com.futsalground.portfolio.player.service;

import com.futsalground.portfolio.exception.ApplyMemberNotFoundException;
import com.futsalground.portfolio.exception.RecruitNotFoundException;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import com.futsalground.portfolio.player.domain.MatchInfo;
import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.domain.TeamInfo;
import com.futsalground.portfolio.player.model.MyApplyShowDto;
import com.futsalground.portfolio.player.model.MyRecruitDto;
import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import com.futsalground.portfolio.player.repository.RecruitCustomRepository;
import com.futsalground.portfolio.player.repository.RecruitApplyRepository;
import com.futsalground.portfolio.player.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;
    private final RecruitApplyRepository recruitApplyRepository;
    private final RecruitCustomRepository recruitCustomRepository;

    @Override
    public Optional<RecruitDto> findById(Long id) {
        return recruitRepository.findById(id).map(recruit -> new RecruitDto(
                recruit.getId(),
                recruit.getRecruitMember(),
                recruit.getTeamInfo().getTeamname(),
                recruit.getTeamInfo().getAges(),
                recruit.getTeamInfo().getPosition(),
                recruit.getTeamInfo().getSkill(),
                recruit.getTeamInfo().getContactway(),
                recruit.getTeamInfo().getPhone(),
                recruit.getMatchInfo().getGroundname(),
                recruit.getMatchInfo().getAddr1(),
                recruit.getMatchInfo().getAddr2(),
                recruit.getMatchInfo().getStarttime(),
                recruit.getMatchInfo().getStarttime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd(E)")),
                recruit.getMatchInfo().getStarttime().format(DateTimeFormatter.ofPattern("HH:mm")),
                recruit.getMatchInfo().getCost(),
                recruit.getVolume(),
                recruit.getApply(),
                recruit.getExplanation(),
                (int)recruit.getApplyMembers().stream().filter(ApplyMember::isSelected).count()
        ));
    }

    @Override
    public void create(RecruitDto recruitDto) {
        recruitRepository.save(recruitDto.toEntity(recruitDto));
    }

    @Override
    public Page<RecruitPageViewDto> findAll(Pageable pageable) {
        Page<Recruit> recruits = recruitRepository.findAll(pageable);
        List<RecruitPageViewDto> recruitPageViewDtos = pageToList(recruits);
        recruitPageViewDtos.sort((o1, o2) -> {
            if (o1.isOpen() && o1.getStarttime().isAfter(o2.getStarttime())) {
                return -1;
            } else {
                return 1;
            }
        });
        return new PageImpl<>(recruitPageViewDtos, pageable, recruits.getTotalElements());
    }

    @Override
    public Page<RecruitPageViewDto> findRecruting(Pageable pageable) {
        Page<Recruit> recruits = recruitCustomRepository.findRecruting(pageable);
        List<RecruitPageViewDto> recruitPageViewDtos = pageToList(recruits);
        for (int i = recruitPageViewDtos.size() - 1; i >= 0; i--) {
            if (recruitPageViewDtos.get(i).getSeledtedMembers() >= recruitPageViewDtos.get(i).getVolume()) {
                recruitPageViewDtos.remove(recruitPageViewDtos.get(i));
            }
        }

        return new PageImpl<>(recruitPageViewDtos, pageable, recruits.getTotalElements());
    }

    @Override
    public void apply(Long id, Member member) throws RecruitNotFoundException {
        Recruit recruit = recruitRepository.findById(id).orElseThrow(RecruitNotFoundException::new);
        ApplyMember applyMember = new ApplyMember(recruit, member);
        recruitApplyRepository.save(applyMember);
    }

    @Override
    public Page<RecruitPageViewDto> findByAddr(Pageable pageable, String addr) {
        Page<Recruit> recruits = recruitCustomRepository.findByAddr1Containing(addr, pageable);
        List<RecruitPageViewDto> recruitPageViewDtos = pageToList(recruits);
        recruitPageViewDtos.sort((o1, o2) -> o1.getStarttime().isAfter(o2.getStarttime()) ? -1 : 1);
        return new PageImpl<>(recruitPageViewDtos, pageable, recruits.getTotalElements());
    }

    @Override
    public Page<RecruitPageViewDto> findByGrdName(Pageable pageable, String grdName) {
        Page<Recruit> recruits = recruitCustomRepository.findByGrdNameContaining(grdName, pageable);
        List<RecruitPageViewDto> recruitPageViewDtos = pageToList(recruits);
        recruitPageViewDtos.sort((o1, o2) -> o1.getStarttime().isAfter(o2.getStarttime()) ? -1 : 1);
        return new PageImpl<>(recruitPageViewDtos, pageable, recruits.getTotalElements());
    }

    private List<RecruitPageViewDto> pageToList(Page<Recruit> recruits) {
        LocalDateTime now = LocalDateTime.now();
        List<RecruitPageViewDto> recruitPageViewDtos = recruits.stream().map(recruit -> new RecruitPageViewDto(
                recruit.getId(),
                recruit.getMatchInfo().getGroundname(),
                recruit.getMatchInfo().getAddr1(),
                recruit.getMatchInfo().getAddr2(),
                recruit.getMatchInfo().getStarttime(),
                recruit.getRecruitMember(),
                (int) recruit.getApplyMembers().stream().filter(ApplyMember::isSelected).count(),
                recruit.getVolume(),
                recruit.getMatchInfo().getStarttime().isAfter(now)
        )).collect(Collectors.toList());
        return recruitPageViewDtos;
    }

    @Override
    public List<MyApplyShowDto> findMyApplys(Member member) {
        return recruitCustomRepository.findRecruitOfApply(member);
    }

    @Override
    public List<MyRecruitDto> findMyRecruit(Member member) {
        return recruitCustomRepository.findMyRecruits(member);
    }

    @Override
    public void removeApply(Long id) {
        recruitApplyRepository.deleteById(id);
    }

    @Override
    public void recruitSelect(Long id) throws ApplyMemberNotFoundException {
        ApplyMember applyMember = recruitApplyRepository.findById(id).orElseThrow(ApplyMemberNotFoundException::new);
        applyMember.changeSelected();
        System.out.println("applyMember.isSelected() = " + applyMember.isSelected());
    }

    @Override
    public void updateRecruit(Long id, RecruitDto recruitDto) throws RecruitNotFoundException {
        Recruit recruit = recruitRepository.findById(id).orElseThrow(RecruitNotFoundException::new);
        recruit.update(TeamInfo.builder()
                        .skill(recruitDto.getSkill())
                        .phone(recruitDto.getPhone())
                        .contactway(recruitDto.getContactway())
                        .ages(recruitDto.getAges())
                        .position(recruitDto.getPosition())
                        .teamname(recruitDto.getTeamname())
                        .build(),
                MatchInfo.builder()
                        .addr1(recruitDto.getAddr1())
                        .addr2(recruitDto.getAddr2())
                        .groundname(recruitDto.getGroundname())
                        .starttime(recruitDto.getStarttime())
                        .cost(recruitDto.getCost())
                        .build(),
                recruitDto.getVolume(),
                recruitDto.getExplanation()
        );
    }

    @Override
    public List<ApplyMember> getApplyMemeber(Long id) {
        return recruitCustomRepository.findApplyMembers(id);
    }

    @Override
    public void delete(Long id) {
        recruitRepository.deleteById(id);
    }
}
