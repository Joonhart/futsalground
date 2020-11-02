package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardViewServiceImpl implements BoardViewService {

    private final BoardRepository boardRepository;

    @Override
    public Optional<BoardViewDto> findById(Long id) {
        return boardRepository.findById(id).map(b ->
                new BoardViewDto(
                        b.getId(),
                        b.getWriter(),
                        b.getTitle(),
                        b.getContent()
                ));
    }

    @Override
    public List<BoardViewDto> findAll() {
        List<BoardViewDto> boardViewDtos = boardRepository.findAll().stream().map(board -> new BoardViewDto(
                board.getId(),
                board.getWriter(),
                board.getTitle(),
                board.getContent()
        )).collect(Collectors.toList());
        return boardViewDtos;
    }
}
