package com.futsalground.portfolio.board.controller;

import com.futsalground.portfolio.board.exception.BoardNotFoundException;
import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.board.service.BoardSaveService;
import com.futsalground.portfolio.board.service.BoardViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String list(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5)
            Pageable pageable, @RequestParam(required = false) String searchText, String searchCond) {
        Page<BoardViewDto> boardViewDtos = null;
        if (searchCond == null || searchCond.equals("")) {
            boardViewDtos = boardViewService.findAll(pageable);
        } else if (searchCond.equals("title")) {
            boardViewDtos = boardViewService.findByTitleContaining(searchText, pageable);
        } else if (searchCond.equals("content")) {
            boardViewDtos = boardViewService.findByContentContaining(searchText, pageable);
        } else if (searchCond.equals("writer")) {
            boardViewDtos = boardViewService.findByWriterContaining(searchText, pageable);
        } else {
            boardViewDtos = boardViewService.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        }
        int startPage = Math.max(1, boardViewDtos.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boardViewDtos.getTotalPages(), boardViewDtos.getPageable().getPageNumber() + 4);
        int curPage = boardViewDtos.getPageable().getPageNumber()+1;
        model.addAttribute("curPage", curPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardViewDtos", boardViewDtos);
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

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute BoardViewDto boardViewDto) throws BoardNotFoundException {
        boardSaveService.updateBoard(id, boardViewDto.getTitle(), boardViewDto.getContent());
        return "redirect:/board/" + id;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        boardSaveService.deleteBoard(id);
        return "redirect:/board";
    }
}
