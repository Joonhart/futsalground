package com.futsalground.portfolio.ground.repository;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.model.GroundSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroundCustomRepository {

    Page<Ground> findAllSearch(Pageable pageable, GroundSearch groundSearch);
}
