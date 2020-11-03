package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.domain.Board;
import com.futsalground.portfolio.board.exception.BoardNotFoundException;
import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardSaveServiceImpl implements BoardSaveService {

    private final BoardRepository boardRepository;

    @Override
    public Long saveBoard(BoardSaveDto boardSaveDto) {
        Board board = Board.builder()
                .writer(boardSaveDto.getWriter())
                .title(boardSaveDto.getTitle())
                .content(boardSaveDto.getContent())
                .build();
        boardRepository.save(board);
        return board.getId();
    }

    @Override
    public void updateBoard(Long id, String title, String content) throws BoardNotFoundException {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.update(title, content);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
