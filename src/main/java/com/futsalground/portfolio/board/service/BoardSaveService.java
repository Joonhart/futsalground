package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.exception.BoardNotFoundException;

public interface BoardSaveService {

    Long saveBoard(BoardSaveDto boardSaveDto);

    void updateBoard(Long id, String title, String content) throws BoardNotFoundException;

    void deleteBoard(Long id);
}
