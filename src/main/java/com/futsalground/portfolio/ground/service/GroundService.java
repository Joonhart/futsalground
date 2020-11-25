package com.futsalground.portfolio.ground.service;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.ground.model.GroundSearch;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import com.futsalground.portfolio.ground.model.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GroundService {

    Optional<Ground> findById(Long id);

    Page<GroundViewDto> findAllGround(Pageable pageable, GroundSearch groundSearch);

//    Page<GroundViewDto> findAllGroundforMember(Pageable pageable, GroundSearch groundSearch);

    Optional<GroundViewDto> findGround(Long id);

    void reservation(ReservationDto reservationDto);

    Optional<Reservation> findReservationById(Long id);

    Page<ReservationDto> findMyRev(String email, Pageable pageable);

    void cancelReservation(Long id, Long memid);

    List<String> findRevs(Long id, LocalDate date);
}
