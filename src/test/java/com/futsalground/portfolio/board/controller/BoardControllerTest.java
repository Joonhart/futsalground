package com.futsalground.portfolio.board.controller;

import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.board.service.BoardSaveService;
import com.futsalground.portfolio.board.service.BoardViewService;
import com.futsalground.portfolio.common.domain.BaseEntity;
import com.futsalground.portfolio.exception.BoardNotFoundException;
import com.futsalground.portfolio.exception.MemberNotFoundException;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import com.futsalground.portfolio.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardControllerTest {

    @Autowired
    BoardSaveService boardSaveService;
    @Autowired
    BoardViewService boardViewService;
    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("게시글 저장")
    public void post() throws MemberNotFoundException {
        MemberSaveDto memberSaveDto = new MemberSaveDto(null, "joonhart@gmail.com", "1234", "sdf", "asd", "sdf");
        memberService.save(memberSaveDto);

        BoardSaveDto boardSaveDto = new BoardSaveDto();
        boardSaveDto.setWriter(memberSaveDto.getEmail());
        boardSaveDto.setTitle("title");
        boardSaveDto.setContent("content");
        Long boardId = boardSaveService.saveBoard(boardSaveDto);

        Optional<BoardViewDto> find = boardViewService.findById(boardId);
        BoardViewDto boardViewDto = find.get();

        MemberViewDto memberViewDto = memberService.findMember(1L).get();

        assertThat(boardViewDto.getWriter()).isEqualTo(boardSaveDto.getWriter());
        assertThat(boardViewDto.getTitle()).isEqualTo(boardSaveDto.getTitle());
        assertThat(boardViewDto.getContent()).isEqualTo(boardSaveDto.getContent());
        assertThat(memberViewDto.getBoardcnt()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 수정")
    public void update() throws BoardNotFoundException, MemberNotFoundException {
        BoardSaveDto boardSaveDto = new BoardSaveDto();
        boardSaveDto.setWriter("writer");
        boardSaveDto.setTitle("title");
        boardSaveDto.setContent("content");
        Long boardId = boardSaveService.saveBoard(boardSaveDto);

        em.flush();
        em.clear();

        Optional<BoardViewDto> find = boardViewService.findById(boardId);
        boardSaveService.updateBoard(boardId, "update title", "update content");

        em.flush();
        em.clear();


        Optional<BoardViewDto> find2 = boardViewService.findById(boardId);
        BoardViewDto boardViewDto = find2.get();

        assertThat(boardViewDto.getWriter()).isEqualTo(boardSaveDto.getWriter());
        assertThat(boardViewDto.getTitle()).isEqualTo("update title");
        assertThat(boardViewDto.getContent()).isEqualTo("update content");

    }
}