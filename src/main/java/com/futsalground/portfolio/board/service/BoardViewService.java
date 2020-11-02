package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.domain.Board;
import com.futsalground.portfolio.board.model.BoardViewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardViewService {

    Optional<BoardViewDto> findById(Long id);

    List<BoardViewDto> findAll();
}
