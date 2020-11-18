package com.futsalground.portfolio.ground.service;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import com.futsalground.portfolio.ground.model.ReservationDto;
import com.futsalground.portfolio.ground.repository.GroundRepository;
import com.futsalground.portfolio.ground.repository.GroundReservationRepository;
import com.futsalground.portfolio.ground.repository.GroundRevCustomRepository;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroundServiceImpl implements GroundService {

    private final GroundRepository groundRepository;
    private final GroundReservationRepository groundReservationRepository;
    private final MemberRepository memberRepository;
    private final GroundRevCustomRepository groundRevCustomRepository;

    @Override
    public Optional<Ground> findById(Long id) {
        return groundRepository.findById(id);
    }

    @Override
    public Page<GroundViewDto> findAllGround(Pageable pageable) {
        Page<Ground> grounds = groundRepository.findAll(pageable);
        List<GroundViewDto> collect = grounds.stream().map(ground -> new GroundViewDto(
                ground.getId(),
                ground.getGrdName(),
                ground.getName(),
                ground.getPhone(),
                ground.getRoadAddr(),
                ground.getNumAddr(),
                ground.getTimeAndCost(),
                ground.getMatchtype(),
                ground.getAmenities(),
                ground.getImages(),
                ground.getSize1(),
                ground.getSize2(),
                ground.getGroundInfo()
        )).collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, grounds.getTotalElements());
    }

    @Override
    public Optional<GroundViewDto> findGround(Long id) {
        Optional<GroundViewDto> groundViewDto = groundRepository.findById(id).map(g -> new GroundViewDto(
                g.getId(),
                g.getGrdName(),
                g.getName(),
                g.getPhone(),
                g.getRoadAddr(),
                g.getNumAddr(),
                g.getTimeAndCost(),
                g.getMatchtype(),
                g.getAmenities(),
                g.getImages(),
                g.getSize1(),
                g.getSize2(),
                g.getGroundInfo()));
        return groundViewDto;
    }

    @Override
    public void reservation(ReservationDto reservationDto) {
        groundReservationRepository.save(reservationDto.toEntity(reservationDto));
        Member member = memberRepository.findByEmail(reservationDto.getEmail()).get();
        member.PlusRev(reservationDto.getCost());
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
        return groundReservationRepository.findById(id);
    }

    @Override
    public Page<ReservationDto> findMyRev(String email, Pageable pageable) {
        Page<Reservation> reservationPage = groundReservationRepository.findByEmail(email, pageable);
        List<ReservationDto> collect = reservationPage.stream().map(reservation -> new ReservationDto(
                reservation.getId(),
                reservation.getEmail(),
                reservation.getCreatedDate(),
                reservation.getMember(),
                reservation.getGround(),
                reservation.getRevDate(),
                reservation.getRevTime(),
                reservation.getCost(),
                reservation.getPayMethod(),
                reservation.getRevDate().isBefore(LocalDate.now())
        )).collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, reservationPage.getTotalElements());
    }

    @Override
    public void cancelReservation(Long id) {
        groundReservationRepository.deleteById(id);
    }

    @Override
    public List<String> findRevs(Long id, LocalDate date) {
        return groundRevCustomRepository.findRevs(id, date);
    }
}
