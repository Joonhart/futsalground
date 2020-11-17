package com.futsalground.portfolio.ground.controller;

import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.ground.model.ReservationDto;
import com.futsalground.portfolio.ground.service.GroundService;
import com.futsalground.portfolio.player.service.RecruitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroundControllerTest {

    @Autowired
    GroundService groundService;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("예약하기 test")
    public void revTest() {
        ReservationDto reservationDto = new ReservationDto("abc", "grdName", "2020-11-17", "abc", 10000, 'm');
        groundService.reservation(reservationDto);

        em.flush();
        em.clear();

        Reservation reservation = groundService.findReservationById(1L).get();
        System.out.println("reservation = " + reservation);

    }

}