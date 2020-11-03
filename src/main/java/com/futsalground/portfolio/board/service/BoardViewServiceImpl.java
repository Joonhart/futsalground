package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.domain.Board;
import com.futsalground.portfolio.board.model.BoardViewDto;
import com.futsalground.portfolio.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Page<BoardViewDto> findAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        List<BoardViewDto> collect = getCollect(boards);
        return new PageImpl<>(collect, pageable, boards.getTotalElements());
    }

    @Override
    public Page<BoardViewDto> findByTitleContaining(String searchText, Pageable pageable) {
        Page<Board> boards = boardRepository.findByTitleContaining(pageable, searchText);
        List<BoardViewDto> collect = getCollect(boards);
        return new PageImpl<>(collect, pageable, boards.getTotalElements());
    }

    @Override
    public Page<BoardViewDto> findByContentContaining(String searchText, Pageable pageable) {
        Page<Board> boards = boardRepository.findByContentContaining(pageable, searchText);
        List<BoardViewDto> collect = getCollect(boards);
        return new PageImpl<>(collect, pageable, boards.getTotalElements());
    }

    @Override
    public Page<BoardViewDto> findByTitleContainingOrContentContaining(String searchText, String searchText2, Pageable pageable) {
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(pageable, searchText2, searchText);
        List<BoardViewDto> collect = getCollect(boards);
        return new PageImpl<>(collect, pageable, boards.getTotalElements());
    }

    @Override
    public Page<BoardViewDto> findByWriterContaining(String searchText, Pageable pageable) {
        Page<Board> boards = boardRepository.findByWriterContaining(pageable, searchText);
        List<BoardViewDto> collect = getCollect(boards);
        return new PageImpl<>(collect, pageable, boards.getTotalElements());
    }

    private List<BoardViewDto> getCollect(Page<Board> boards) {
        List<BoardViewDto> collect = boards.stream().map(board -> new BoardViewDto(
                board.getId(),
                board.getWriter(),
                board.getTitle(),
                board.getContent()
        )).collect(Collectors.toList());
        return collect;
    }
}
