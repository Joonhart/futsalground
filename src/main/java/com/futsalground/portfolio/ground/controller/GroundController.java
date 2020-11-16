package com.futsalground.portfolio.ground.controller;

import com.futsalground.portfolio.exception.BoardNotFoundException;
import com.futsalground.portfolio.exception.GroundNotFoundException;
import com.futsalground.portfolio.ground.model.GroundViewDto;
import com.futsalground.portfolio.ground.service.GroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/ground")
@RequiredArgsConstructor
public class GroundController {

    private final GroundService groundService;

    @GetMapping
    public String list(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5)
                       Pageable pageable) {
        Page<GroundViewDto> groundViewDtos = groundService.findAllGround(pageable);
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("groundViewDtos", groundViewDtos);
        return "ground/groundList";
    }

    @GetMapping("{id}")
    private String groundView(@PathVariable Long id, Model model) throws GroundNotFoundException {
        Optional<GroundViewDto> groundViewDto = groundService.findGround(id);
        model.addAttribute("groundViewDto", groundViewDto.orElseThrow(GroundNotFoundException::new));
        return "ground/groundView";
    }
}
