package com.futsalground.portfolio.ground.repository;

import java.time.LocalDate;
import java.util.List;

public interface GroundRevCustomRepository {

    List<String> findRevs(Long id, LocalDate date);
}
