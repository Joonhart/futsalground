package com.futsalground.portfolio.ground.repository;

import com.futsalground.portfolio.ground.domain.Ground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroundRepository extends JpaRepository<Ground, Long> {
}
