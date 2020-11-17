package com.futsalground.portfolio.ground.service;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import com.futsalground.portfolio.ground.model.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GroundService {

    Page<GroundViewDto> findAllGround(Pageable pageable);

    Optional<GroundViewDto> findGround(Long id);

    void reservation(ReservationDto reservationDto);

    Optional<Reservation> findReservationById(Long id);

    Page<Reservation> findMyRev(String email, Pageable pageable);
}
