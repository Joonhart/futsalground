package com.futsalground.portfolio.ground.controller;

import com.futsalground.portfolio.exception.GroundNotFoundException;
import com.futsalground.portfolio.exception.MemberNotFoundException;
import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.model.GroundSearch;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import com.futsalground.portfolio.ground.model.ReservationDto;
import com.futsalground.portfolio.ground.model.ReservationShowDto;
import com.futsalground.portfolio.ground.service.GroundService;
import com.futsalground.portfolio.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ground")
@RequiredArgsConstructor
public class GroundController {

    private final GroundService groundService;

    @GetMapping
    public String list(@ModelAttribute GroundSearch groundSearch, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5)
                       Pageable pageable, HttpServletRequest request) {
        Page<GroundViewDto> groundViewDtos = null;
        if ((groundSearch.getAddr1() == null || groundSearch.getAddr1().equals("")) && (groundSearch.getAddr2() == null || groundSearch.getAddr2().equals("")) && (groundSearch.getGrdName() == null || groundSearch.getGrdName().equals(""))) {
            groundSearch.setAddr1("");           groundSearch.setAddr2("");           groundSearch.setGrdName("");
            if (request.isRequestedSessionIdValid()) {
                HttpSession session = request.getSession();
                Member member = (Member) session.getAttribute("member");
                if (groundSearch.getAddr1().equals(""))    groundSearch.setAddr1(member.getAddr1());
                if (groundSearch.getAddr2().equals(""))    groundSearch.setAddr2(member.getAddr2());
                groundViewDtos = groundService.findAllGroundforMember(pageable, groundSearch);
                groundSearch.setAddr1("");
                groundSearch.setAddr2("");
            } else {
                groundViewDtos = groundService.findAllGround(pageable, groundSearch);
            }
        } else {
            groundViewDtos = groundService.findAllSearch(pageable, groundSearch);
        }
        int startPage = Math.max(1, groundViewDtos.getPageable().getPageNumber() - 4);
        int endPage = Math.min(groundViewDtos.getTotalPages(), groundViewDtos.getPageable().getPageNumber() + 4);
        if (startPage > endPage)  endPage = startPage;
        int curPage = groundViewDtos.getPageable().getPageNumber()+1;
        int totalPage = groundViewDtos.getTotalPages() == 0 ? 1 : groundViewDtos.getTotalPages();
        List<String> revs = new ArrayList<>();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("groundViewDtos", groundViewDtos);
        model.addAttribute("revs", revs);
        return "ground/groundList";
    }

    @GetMapping("{id}")
    private String groundView(@PathVariable Long id, Model model) throws GroundNotFoundException {
        Optional<GroundViewDto> groundViewDto = groundService.findGround(id);
        List<String> revs = groundService.findRevs(id, LocalDate.now());
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("reservationDto", new ReservationDto());
        model.addAttribute("groundViewDto", groundViewDto.orElseThrow(GroundNotFoundException::new));
        model.addAttribute("revs", revs);
        return "ground/groundView";
    }

    @PostMapping("/showReservation")
    private String showReservation(ReservationShowDto reservationShowDto, Model model) {
        GroundViewDto groundViewDto = groundService.findGround(reservationShowDto.getId()).get();
        List<String> revs = groundService.findRevs(reservationShowDto.getId(), LocalDate.parse(reservationShowDto.getDate().substring(0, 10), DateTimeFormatter.ISO_DATE));
        LocalDate localDate = LocalDate.parse(reservationShowDto.getDate().substring(0, reservationShowDto.getDate().length()-3));
        LocalDateTime now = LocalDateTime.of(localDate, LocalTime.now());
        model.addAttribute("groundViewDto", groundViewDto);
        model.addAttribute("now", now);
        model.addAttribute("revs", revs);
        return "ground/groundView :: #timeWrap";
    }

    @PostMapping("/showReservationList")
    private String showReservationList(ReservationShowDto reservationShowDto, Model model) {
        GroundViewDto groundViewDto = groundService.findGround(reservationShowDto.getId()).get();
        List<String> revs = groundService.findRevs(reservationShowDto.getId(), LocalDate.parse(reservationShowDto.getDate().substring(0, 10), DateTimeFormatter.ISO_DATE));
        groundViewDto.setRevs(revs);
        LocalDate localDate = LocalDate.parse(reservationShowDto.getDate().substring(0, reservationShowDto.getDate().length()-3));
        LocalDateTime now = LocalDateTime.of(localDate, LocalTime.now());
        model.addAttribute("groundViewDto", groundViewDto);
        model.addAttribute("now", now);
        return "ground/groundList :: #timeWrap";
    }

    @PostMapping("/createReservation")
    public String reservation(ReservationDto reservationDto, String reserveDate, Long grdId, HttpServletRequest request) throws MemberNotFoundException, GroundNotFoundException {
        LocalDate revDate = LocalDate.parse(reserveDate, DateTimeFormatter.ISO_DATE);
        reservationDto.setRevDate(revDate);
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        reservationDto.setMember(member);
        Ground ground = groundService.findById(grdId).orElseThrow(GroundNotFoundException::new);
        reservationDto.setEmail(member.getEmail());
        reservationDto.setGround(ground);
        groundService.reservation(reservationDto);
        return "redirect:/member/revInfo";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable Long id, HttpServletRequest request) throws MemberNotFoundException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        groundService.cancelReservation(id, member.getId());
        return "redirect:/member/revInfo";
    }
}
