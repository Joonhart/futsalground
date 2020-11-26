package com.futsalground.portfolio.board.service;

import com.futsalground.portfolio.board.domain.Board;
import com.futsalground.portfolio.board.model.BoardSaveDto;
import com.futsalground.portfolio.board.repository.BoardRepository;
import com.futsalground.portfolio.exception.BoardNotFoundException;
import com.futsalground.portfolio.exception.MemberNotFoundException;
import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardSaveServiceImpl implements BoardSaveService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long saveBoard(BoardSaveDto boardSaveDto) throws MemberNotFoundException {
        Board board = Board.builder()
                .writer(boardSaveDto.getWriter())
                .title(boardSaveDto.getTitle())
                .content(boardSaveDto.getContent())
                .build();
        boardRepository.save(board);
        Member member = memberRepository.findByEmail(board.getWriter()).orElseThrow(MemberNotFoundException::new);
        member.plusBoardCnt();
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
