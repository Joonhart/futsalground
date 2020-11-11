package com.futsalground.portfolio.player.service;


import com.futsalground.portfolio.player.model.ApplyDto;
import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ApplyService {

    Optional<ApplyDto> findById(Long id);

    void create(ApplyDto applyDto);

    Page<ApplyDto> findAll(Pageable pageable);
}
