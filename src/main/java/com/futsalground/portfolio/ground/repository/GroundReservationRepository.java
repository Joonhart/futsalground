package com.futsalground.portfolio.ground.repository;

import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GroundReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findByEmail(String email, Pageable pageable);
}
