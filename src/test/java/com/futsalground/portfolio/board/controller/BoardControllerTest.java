package com.futsalground.portfolio.board.controller;

import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.board.service.BoardSaveService;
import com.futsalground.portfolio.board.service.BoardViewService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardControllerTest {

    private final BoardSaveService boardSaveService;
    private final BoardViewService boardViewService;
    private final EntityManager em;

    public BoardControllerTest(BoardSaveService boardSaveService, BoardViewService boardViewService, EntityManager em) {
        this.boardSaveService = boardSaveService;
        this.boardViewService = boardViewService;
        this.em = em;
    }

}