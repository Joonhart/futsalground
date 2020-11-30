package com.futsalground.portfolio.ground.service;

import com.futsalground.portfolio.exception.MemberNotFoundException;
import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.ground.model.GroundSearch;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import com.futsalground.portfolio.ground.model.ReservationDto;
import com.futsalground.portfolio.ground.repository.GroundCustomRepository;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final GroundCustomRepository groundCustomRepository;

    @Override
    public Optional<Ground> findById(Long id) {
        return groundRepository.findById(id);
    }

    @Override
    public Page<GroundViewDto> findAllGround(Pageable pageable, GroundSearch groundSearch) {
        Page<Ground> grounds = groundCustomRepository.findAllSearch(pageable, groundSearch);
        return entityToDto(pageable, grounds);
    }

    @Override
    public Page<GroundViewDto> findAllGroundforMember(Pageable pageable, GroundSearch groundSearch) {
        Page<Ground> grounds = groundCustomRepository.findAllGroundforMember(pageable, groundSearch);
        return entityToDto(pageable, grounds);
    }

    @Override
    public Page<GroundViewDto> findAllSearch(Pageable pageable, GroundSearch groundSearch) {
        Page<Ground> grounds = groundCustomRepository.findAllSearch(pageable, groundSearch);
        return entityToDto(pageable, grounds);
    }

    private Page<GroundViewDto> entityToDto(Pageable pageable, Page<Ground> grounds) {
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
                ground.getGroundInfo(),
                LocalDate.now(),
                LocalTime.now().getHour(),
                groundRevCustomRepository.findRevs(ground.getId(), LocalDate.now())
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
                g.getGroundInfo(),
                LocalDate.now(),
                LocalTime.now().getHour(),
                null));
        return groundViewDto;
    }

    @Override
    public void reservation(ReservationDto reservationDto) throws MemberNotFoundException {
        groundReservationRepository.save(reservationDto.toEntity(reservationDto));
        Member member = memberRepository.findByEmail(reservationDto.getEmail()).orElseThrow(MemberNotFoundException::new);
        member.plusRev(reservationDto.getCost());
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
        return groundReservationRepository.findById(id);
    }

    @Override
    public Page<ReservationDto> findMyRev(String email, Pageable pageable) {
        LocalDate nowDate = LocalDate.now();
        int hour = LocalTime.now().getHour();
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
                !reservation.getRevDate().isBefore(nowDate) && (reservation.getRevDate().isEqual(nowDate) == (Integer.parseInt(reservation.getRevTime().substring(0, 2)) <= hour))
        )).collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, reservationPage.getTotalElements());
    }

    @Override
    public void cancelReservation(Long id, Long memid) throws MemberNotFoundException {
        Reservation reservation = groundReservationRepository.findById(id).get();
        groundReservationRepository.deleteById(id);
        Member member = memberRepository.findById(memid).orElseThrow(MemberNotFoundException::new);
        member.cancelRev(reservation.getCost());
    }

    @Override
    public List<String> findRevs(Long id, LocalDate date) {
        return groundRevCustomRepository.findRevs(id, date);
    }
}
