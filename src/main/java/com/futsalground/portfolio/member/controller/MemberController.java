package com.futsalground.portfolio.member.controller;

import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.exception.MemberNotFoundException;
import com.futsalground.portfolio.exception.UserNameDuplicateException;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final HttpSession session;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberSaveDto", new MemberSaveDto());
        return "member/login";
    }

    @PostMapping("/login")
    public String login(MemberSaveDto memberSaveDto) throws MemberNotFoundException {
        Optional<Member> member = memberService.findByEmailAndPassword(memberSaveDto.getEmail(), memberSaveDto.getPassword());
        System.out.println("member.get() = " + member.get());
        if (member.isEmpty()) {
            throw new MemberNotFoundException();
        }
        session.setAttribute("member", member.get());
        session.setAttribute("today", LocalDateTime.now());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
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
    public String myPage() {
        if (session.getAttribute("member") == null) {
            return "member/login";
        }
        return "member/mypage";
    }
}
