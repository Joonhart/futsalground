package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.exception.BoardNotFoundException;
import com.futsalground.portfolio.board.model.BoardSaveDto;

public interface BoardSaveService {

    Long saveBoard(BoardSaveDto boardSaveDto);

    Long updateBoard(BoardSaveDto boardSaveDto) throws BoardNotFoundException;

    void deleteBoard(Long id);
}
