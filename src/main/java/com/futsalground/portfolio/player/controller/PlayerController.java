package com.futsalground.portfolio.player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    @GetMapping
    public String list() {
        return "player/player";
    }
}
