package com.futsalground.portfolio.ground.service;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GroundService {


    Page<GroundViewDto> findAllGround(Pageable pageable);

    Optional<GroundViewDto> findGround(Long id);
}
