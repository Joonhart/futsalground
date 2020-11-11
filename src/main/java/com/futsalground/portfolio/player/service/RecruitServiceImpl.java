package com.futsalground.portfolio.player.service;

import com.futsalground.portfolio.player.domain.Recruit;
import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import com.futsalground.portfolio.player.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;

    @Override
    public Optional<RecruitDto> findById(Long id) {
        return recruitRepository.findById(id).map(recruit -> new RecruitDto(
                recruit.getId(),
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
                recruit.getExplanation()
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
        return new PageImpl<>(recruitPageViewDtos, pageable, recruits.getTotalElements());
    }

//    @Override
//    public Page<RecruitPageViewDto> findByAddr1(String addr1, Pageable pageable) {
//        Page<Recruit> recruits = recruitRepository.findByAddr1(addr1, pageable);
//        List<RecruitPageViewDto> recruitPageViewDtos = pageToList(recruits);
//        return new PageImpl<>(recruitPageViewDtos, pageable, recruits.getTotalElements());
//    }
//
//    @Override
//    public Page<RecruitPageViewDto> findByGroundname(String groundname, Pageable pageable) {
//        Page<Recruit> recruits = recruitRepository.findByGroundname(groundname, pageable);
//        List<RecruitPageViewDto> recruitPageViewDtos = pageToList(recruits);
//        return new PageImpl<>(recruitPageViewDtos, pageable, recruits.getTotalElements());
//    }

    // 날짜로 검색 추가

    private List<RecruitPageViewDto> pageToList(Page<Recruit> recruits) {
        List<RecruitPageViewDto> recruitPageViewDtos = recruits.stream().map(recruit -> new RecruitPageViewDto(
                recruit.getId(),
                recruit.getMatchInfo().getGroundname(),
                recruit.getMatchInfo().getAddr1(),
                recruit.getMatchInfo().getAddr2(),
                recruit.getMatchInfo().getStarttime(),
                recruit.getVolume()
        )).collect(Collectors.toList());
        return recruitPageViewDtos;
    }
}
