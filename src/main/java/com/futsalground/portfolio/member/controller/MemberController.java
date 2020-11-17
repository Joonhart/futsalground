package com.futsalground.portfolio.member.controller;

import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.exception.MemberNotFoundException;
import com.futsalground.portfolio.exception.UserNameDuplicateException;
import com.futsalground.portfolio.ground.domain.Reservation;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import com.futsalground.portfolio.ground.service.GroundService;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import com.futsalground.portfolio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final GroundService groundService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberSaveDto", new MemberSaveDto());
        return "member/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, MemberSaveDto memberSaveDto) throws MemberNotFoundException {
        Optional<Member> member = memberService.findByEmailAndPassword(memberSaveDto.getEmail(), memberSaveDto.getPassword());
        System.out.println("member.get() = " + member.get());
        if (member.isEmpty()) {
            throw new MemberNotFoundException();
        }
        HttpSession session = request.getSession();
        session.setAttribute("member", member.get());
        session.setAttribute("today", LocalDateTime.now());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberSaveDto", new MemberSaveDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberSaveDto memberSaveDto, BindingResult result) throws UserNameDuplicateException {
        if (result.hasErrors()) {
            return "member/join";
        }
        System.out.println("memberSaveDto = " + memberSaveDto);
        if (!memberService.checkDuplicateId(memberSaveDto.getEmail())) {
            throw new UserNameDuplicateException();
        }
        memberService.save(memberSaveDto);
        return "redirect:/member/login";
    }

    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("member") == null) {
            return "member/login";
        }
        Member member = (Member) session.getAttribute("member");
        Optional<MemberViewDto> memberViewDto = memberService.findMember(member.getId());
        model.addAttribute("memberViewDto", memberViewDto.get());
        return "member/mypage";
    }

    @GetMapping("/revInfo")
    public String revInfo(HttpServletRequest request, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5)
            Pageable pageable) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        String email = member.getEmail();
        Page<Reservation> reservations = groundService.findMyRev(email, pageable);
        int startPage = Math.max(1, reservations.getPageable().getPageNumber() - 4);
        int endPage = Math.min(reservations.getTotalPages(), reservations.getPageable().getPageNumber() + 4);
        if (startPage > endPage)  endPage = startPage;
        int curPage = reservations.getPageable().getPageNumber()+1;
        int totalPage = reservations.getTotalPages() == 0 ? 1 : reservations.getTotalPages();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("reservations", reservations);
        return "member/revInfo";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberViewDto memberViewDto) {
        memberService.update(memberViewDto.getEmail(), memberViewDto.getAddr1(), memberViewDto.getAddr2(), memberViewDto.getPosition());
        return "redirect:/member/mypage";
    }

    @PostMapping("/changePW")
    public String changePW(String email, String newPW) {
        memberService.changePW(email, newPW);
        return "redirect:/member/mypage";
    }
}
