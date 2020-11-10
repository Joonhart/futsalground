package com.futsalground.portfolio.player.service;

import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RecruitService {

    Optional<RecruitDto> findById(Long id);

    void create(RecruitDto recruitDto);

    Page<RecruitPageViewDto> findAll(Pageable pageable);

//    Page<RecruitPageViewDto> findByAddr1(String addr1, Pageable pageable);
//
//    Page<RecruitPageViewDto> findByGroundname(String groundname, Pageable pageable);
}
