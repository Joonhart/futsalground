package com.futsalground.portfolio.member.controller;

import com.futsalground.portfolio.board.exception.UserNameDuplicateException;
import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberSaveDto memberSaveDto, BindingResult result) {
        if (result.hasErrors()) {
            return "member/join";
        }
        System.out.println("memberSaveDto = " + memberSaveDto);
        memberService.save(memberSaveDto);
        return "redirect:/";
    }
}
