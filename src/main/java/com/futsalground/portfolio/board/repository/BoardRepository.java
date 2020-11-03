package com.futsalground.portfolio.board.repository;

import com.futsalground.portfolio.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByTitleContaining(Pageable pageable, String searchText);

    Page<Board> findByContentContaining(Pageable pageable, String searchText);

    Page<Board> findByTitleContainingOrContentContaining(Pageable pageable, String searchText, String searchText2);

    Page<Board> findByWriterContaining(Pageable pageable, String searchText);
}
