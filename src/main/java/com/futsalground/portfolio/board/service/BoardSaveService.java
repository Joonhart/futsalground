package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.exception.BoardNotFoundException;
import com.futsalground.portfolio.exception.MemberNotFoundException;

public interface BoardSaveService {

    Long saveBoard(BoardSaveDto boardSaveDto) throws MemberNotFoundException;

    void updateBoard(Long id, String title, String content) throws BoardNotFoundException;

    void deleteBoard(Long id);
}
