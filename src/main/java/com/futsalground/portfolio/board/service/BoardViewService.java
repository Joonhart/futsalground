package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.model.BoardViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardViewService {

    Optional<BoardViewDto> findById(Long id);

    Page<BoardViewDto> findAll(Pageable pageable);

    Page<BoardViewDto> findByTitleContaining(String searchText, Pageable pageable);

    Page<BoardViewDto> findByContentContaining(String searchText, Pageable pageable);

    Page<BoardViewDto> findByTitleContainingOrContentContaining(String searchText, String searchText2, Pageable pageable);

    Page<BoardViewDto> findByWriterContaining(String searchText, Pageable pageable);
}
