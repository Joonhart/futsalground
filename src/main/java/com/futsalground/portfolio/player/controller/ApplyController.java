package com.futsalground.portfolio.player.controller;

import com.futsalground.portfolio.exception.ApplyNotFoundException;
import com.futsalground.portfolio.player.model.ApplyDto;
import com.futsalground.portfolio.player.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    @GetMapping
    public String applyList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5)
            Pageable pageable) {
        Page<ApplyDto> applyDtos = applyService.findAll(pageable);
        int startPage = Math.max(1, applyDtos.getPageable().getPageNumber() - 4);
        int endPage = Math.min(applyDtos.getTotalPages(), applyDtos.getPageable().getPageNumber() + 4);
        if (startPage > endPage) endPage = startPage;
        int curPage = applyDtos.getPageable().getPageNumber() + 1;
        int totalPage = applyDtos.getTotalPages() == 0 ? 1 : applyDtos.getTotalPages();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("curPage", curPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("applyDtos", applyDtos);
        return "player/applyList";
    }

    @GetMapping("/createApply")
    public String createApply(Model model) {
        model.addAttribute("applyDto", new ApplyDto());
        return "player/applyForm";
    }

    @PostMapping("/createApply")
    public String createR(@Valid ApplyDto applyDto, BindingResult result) {
        if (result.hasErrors()) {
            return "player/applyForm";
        }
//        String day = applyDto.getDay();
//        String time = applyDto.getTime();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        LocalDateTime dateTime = LocalDateTime.parse(day.substring(0, day.length() - 3) + " " + time + ":00.000", formatter);
//        applyDto.setStarttime(dateTime);
        applyService.create(applyDto);
        return "redirect:/apply";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) throws ApplyNotFoundException {
        Optional<ApplyDto> applyDto = applyService.findById(id);
        model.addAttribute("applyDto", applyDto.orElseThrow(ApplyNotFoundException::new));
        return "player/applyView";
    }
}