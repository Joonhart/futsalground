package com.futsalground.portfolio.board.controller;

import com.futsalground.portfolio.board.exception.BoardNotFoundException;
import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.board.service.BoardSaveService;
import com.futsalground.portfolio.board.service.BoardViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardSaveService boardSaveService;
    private final BoardViewService boardViewService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("boardViewDtos", boardViewService.findAll());
        return "board/boardList";
    }

    @GetMapping("/post")
    public String boardPost(Model model) {
        model.addAttribute("boardSaveDto", new BoardSaveDto());
        return "board/boardForm";
    }

    @PostMapping("/post")
    public String post(@Valid BoardSaveDto boardSaveDto, BindingResult result) {
        if (result.hasErrors()) {
            return "board/boardForm";
        }
        Long postNum = boardSaveService.saveBoard(boardSaveDto);
        return "redirect:/board/" + postNum;
    }

    @GetMapping("/{id}")
    public String getBy(@PathVariable Long id, Model model) throws BoardNotFoundException {
        Optional<BoardViewDto> boardViewDto = boardViewService.findById(id);
        model.addAttribute("boardViewDto", boardViewDto.orElseThrow(BoardNotFoundException::new));
        return "board/boardView";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable Long id, Model model) throws BoardNotFoundException {
        Optional<BoardViewDto> boardViewDto = boardViewService.findById(id);
        model.addAttribute("boardViewDto", boardViewDto.orElseThrow(BoardNotFoundException::new));
        return "board/updateForm";
    }

    @PutMapping("{id}/edit")
    public String update(@Valid BoardSaveDto boardSaveDto, Model model) throws BoardNotFoundException {
        boardSaveService.updateBoard(boardSaveDto);
        model.addAttribute("boardViewDto", boardViewService.findById(boardSaveDto.getId()).orElseThrow(BoardNotFoundException::new));
        return "board/boardView";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        boardSaveService.deleteBoard(id);
        return "redirect:/board";
    }


}
