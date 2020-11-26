package com.futsalground.portfolio.player.controller;

import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.exception.ApplyMemberNotFoundException;
import com.futsalground.portfolio.exception.BoardNotFoundException;
import com.futsalground.portfolio.exception.RecruitNotFoundException;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.player.domain.ApplyMember;
import com.futsalground.portfolio.player.model.IdDto;
import com.futsalground.portfolio.player.model.MyRecruitDto;
import com.futsalground.portfolio.player.model.RecruitDto;
import com.futsalground.portfolio.player.model.RecruitPageViewDto;
import com.futsalground.portfolio.player.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @GetMapping
    public String list(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5)
            Pageable pageable) {
        Page<RecruitPageViewDto> recruitPageViewDtos = recruitService.findAll(pageable);
        int startPage = Math.max(1, recruitPageViewDtos.getPageable().getPageNumber() - 4);
        int endPage = Math.min(recruitPageViewDtos.getTotalPages(), recruitPageViewDtos.getPageable().getPageNumber() + 4);
        if (startPage > endPage) endPage = startPage;
        int curPage = recruitPageViewDtos.getPageable().getPageNumber() + 1;
        int totalPage = recruitPageViewDtos.getTotalPages() == 0 ? 1 : recruitPageViewDtos.getTotalPages();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("recruitPageViewDtos", recruitPageViewDtos);
        return "player/recruitList";
    }

    @GetMapping("/createRecruit")
    public String createRecruit(Model model) {
        model.addAttribute("recruitDto", new RecruitDto());
        return "player/recruitForm";
    }

    @PostMapping("/createRecruit")
    public String createR(@Valid RecruitDto recruitDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "player/recruitForm";
        }
        String day = recruitDto.getDay();
        String time = recruitDto.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(day.substring(0, day.length() - 3) + " " + time + ":00.000", formatter);
        recruitDto.setStarttime(dateTime);
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        recruitDto.setRecruitMember(member);
        recruitService.create(recruitDto);
        return "redirect:/recruit";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) throws RecruitNotFoundException {
        Optional<RecruitDto> recruitDto = recruitService.findById(id);
        model.addAttribute("recruitDto", recruitDto.orElseThrow(RecruitNotFoundException::new));
        return "player/recruitView";
    }

    @GetMapping("{id}/update")
    public String updateRecruit(@PathVariable Long id, Model model) throws RecruitNotFoundException {
        Optional<RecruitDto> recruitDto = recruitService.findById(id);
        model.addAttribute("recruitDto", recruitDto.orElseThrow(RecruitNotFoundException::new));
        return "player/recruitUpdate";
    }

    @PostMapping("{id}/update")
    public String updateRecruit(@PathVariable Long id, @ModelAttribute RecruitDto recruitDto) throws RecruitNotFoundException {
        String day = recruitDto.getDay();
        String time = recruitDto.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(day.substring(0, day.length() - 3) + " " + time + ":00.000", formatter);
        recruitDto.setStarttime(dateTime);
        recruitService.updateRecruit(id, recruitDto);
        return "redirect:/recruit/" + id;
    }

    @PostMapping("/{id}/apply")
    public String apply(@PathVariable Long id, HttpServletRequest request) throws RecruitNotFoundException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        recruitService.apply(id, member);
        return "redirect:/member/myApply";
    }

    @PostMapping("applyCancel/{id}")
    public String cancelApply(@PathVariable Long id) {
        recruitService.removeApply(id);
        return "redirect:/member/myApply";
    }

    @GetMapping("select/{id}")
    public String recruitSelect(@PathVariable Long id, Model model) {
        List<ApplyMember> applyMembers = recruitService.getApplyMemeber(id);
        model.addAttribute("applyMembers", applyMembers);
        model.addAttribute("recruitId", id);
        return "member/recruitSelect";
    }

    @PostMapping("/choice")
    public String choiceMember(String applyId, String recruitId, Model model) throws ApplyMemberNotFoundException {
        recruitService.recruitSelect(Long.parseLong(applyId));
        List<ApplyMember> applyMembers = recruitService.getApplyMemeber(Long.parseLong(recruitId));
        model.addAttribute("applyMembers", applyMembers);
        model.addAttribute("recruitId", recruitId);
        return "member/recruitSelect";
    }

    @GetMapping("{id}/delete")
    public String recruitDelete(@PathVariable Long id) {
        recruitService.delete(id);
        return "redirect:/recruit";
    }
}